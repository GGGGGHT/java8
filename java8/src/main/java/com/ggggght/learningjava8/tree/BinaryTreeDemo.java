package com.ggggght.learningjava8.tree;



import java.util.Objects;

/**
 * @author ght
 * @Desc
 * @date 2020-05-18 17:22
 */

@SuppressWarnings("all")
public class BinaryTreeDemo {
    public static void main(String[] args) {
        Hero root = new Hero(1,"zs");
        Hero node2 = new Hero(2,"ls");
        Hero node3 = new Hero(3,"ww");
        Hero node4 = new Hero(4,"zl");
        Hero node5 = new Hero(5,"cs");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        BinaryTree binaryTree = new BinaryTree(root);
        System.out.println(binaryTree.get(3));
        /*System.out.println("preOrder: ");
        binaryTree.preOrder();
        System.out.println("infixOrder: ");
        binaryTree.infixOrder();
        System.out.println("postOrder: ");
        binaryTree.postOrder();*/

    }
}

class BinaryTree {
    private Hero root;

    public BinaryTree(Hero root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (Objects.isNull(root)) {
            System.out.println("二叉树为空");
            return;
        }
        root.preOrder();
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (Objects.isNull(root)) {
            System.out.println("二叉树为空");
            return;
        }
        root.infixOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (Objects.isNull(root)) {
            System.out.println("二叉树为空");
            return;
        }
        root.postOrder();
    }

    public Hero get(int id) {
        return root.get(id);
    }
}

class Hero {
    private Integer id;
    private String name;
    private Hero left;
    private Hero right;

    public Hero getLeft() {
        return left;
    }

    public void setLeft(Hero left) {
        this.left = left;
    }

    public Hero getRight() {
        return right;
    }

    public void setRight(Hero right) {
        this.right = right;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Hero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (!Objects.isNull(this.getLeft())) {
            this.left.preOrder();
        }
        if (!Objects.isNull(this.getRight())) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (!Objects.isNull(this.getLeft())) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (!Objects.isNull(this.getRight())) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (!Objects.isNull(this.getLeft())) {
            this.left.postOrder();
        }
        if (!Objects.isNull(this.getRight())) {
            this.right.postOrder();
        }
        System.out.println(this);

    }

    public Hero get(int id) {
        // 在左边找
        if (this != null) {
            if (this.id > id) {
                return this.left.get(id);
            } else if (this.id < id) {
                // 在右边找
                return this.right.get(id);
            }else {
                return this;
            }
        }
        return null;
    }
}
