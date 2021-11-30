
public class Preview {
    public static void main(String[] args) {
        String stringBlock =
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <body>\n" +
                "        <h1>\"Hello World!\"</h1>\n" +
                "    </body>\n" +
                "</html>\n";

        String textBlock = """
                <!DOCTYPE html>
                <html>
                    <body>
                        <h1>"Hello World!"</h1>
                    </body>
                </html>
                """;

        System.out.println(
                "Does the text block equal to the regular string? " +
                stringBlock.equals(textBlock));
        System.out.println(
                "Does the text block refer to the regular string? " +
                (stringBlock == textBlock));
    }
}
