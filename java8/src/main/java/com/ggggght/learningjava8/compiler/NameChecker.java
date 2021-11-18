package com.ggggght.learningjava8.compiler;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;

import java.util.EnumSet;
import java.util.Objects;

import static javax.lang.model.element.Modifier.*;
import static javax.tools.Diagnostic.Kind.*;

@SuppressWarnings("all")
/**
 * @author 75685
 */
public class NameChecker {

	private final Messager messager;

	private final NameCheckScanner SCANNER = new NameCheckScanner();

	public NameChecker(ProcessingEnvironment processingEnv) {
		this.messager = processingEnv.getMessager();
	}

	public void checkNames(Element e) {
		SCANNER.scan(e);
	}

	private class NameCheckScanner extends ElementScanner8<Void, Void> {

		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCase(e, true);
			super.visitType(e, p);

			return null;
		}

		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {
			if (e.getKind().equals(ElementKind.METHOD)) {
				final Name name = e.getSimpleName();
				if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
					messager.printMessage(WARNING, "method name: \"" + name + "\" chong fu", e);
				}
				checkCamelCase(e, false);
			}

			super.visitExecutable(e, p);

			return null;
		}

		@Override
		public Void visitVariable(VariableElement e, Void p) {
			if (e.getKind() == ElementKind.ENUM_CONSTANT || Objects.isNull(e.getConstantValue())
					|| heuristicallyConstant(e)) {
				checkAllCaps(e);
			}
			else {
				checkCamelCase(e, false);
			}
			return super.visitVariable(e, p);
		}

		private void checkAllCaps(Element e) {
			final String name = e.getSimpleName().toString();

			boolean conventiolan = true;
			final int firstCodePoint = name.codePointAt(0);

			if (!Character.isUpperCase(firstCodePoint)) {
				conventiolan = false;
			}
			else {
				boolean previousUnderscore = false;
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);

					if (cp == (int) '_') {
						if (previousUnderscore) {
							conventiolan = false;
							break;
						}
						previousUnderscore = true;
					}
					else {
						previousUnderscore = false;
						if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
							conventiolan = false;
							break;
						}
					}
				}
			}

			if (!conventiolan) {
				messager.printMessage(WARNING, "constant:  \"" + name + "\" must be upper case!", e);
			}
		}

		@Override
		public Void scan(Element e, Void unused) {
			return super.scan(e, unused);
		}

	}

	private void checkCamelCase(Element e, boolean initalCaps) {
		final String name = e.getSimpleName().toString();
		boolean previousUpper = false;
		boolean conventional = true;
		int firstCodePoint = name.codePointAt(0);

		if (Character.isUpperCase(firstCodePoint)) {
			previousUpper = true;
			if (!initalCaps) {
				messager.printMessage(WARNING, "name: \"" + name + "\" should be lower case", e);
				return;
			}

			if (Character.isLowerCase(firstCodePoint)) {
				messager.printMessage(WARNING, "name: \"" + name + "\" should upper case ", e);
				return;
			}
		}
		else {
			conventional = false;
		}

		if (conventional) {
			int cp = firstCodePoint;
			for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
				cp = name.codePointAt(i);
				if (Character.isUpperCase(cp)) {
					if (previousUpper) {
						conventional = false;
						break;
					}
					previousUpper = true;
				}
				else {
					previousUpper = false;
				}
			}
		}

		if (!conventional) {
			messager.printMessage(WARNING, "name \"" + name + "\" must be camel case", e);
		}
	}

	/**
	 * @param e
	 * @return
	 */
	private boolean heuristicallyConstant(VariableElement e) {
		if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
			return true;
		}

		if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(PUBLIC, STATIC, FINAL))) {
			return true;
		}

		return false;
	}

}
