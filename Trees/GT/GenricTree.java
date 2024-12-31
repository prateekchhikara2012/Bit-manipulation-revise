package Trees.GT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GenricTree {

    Scanner sc = new Scanner(System.in);

    private class node {
        int data;
        ArrayList<node> children = new ArrayList<>();
    }

    private node root;

    public GenricTree() // ! CONSTRUCTOR
    {
        root = implementation(null, 0); // ! AGAr parent me walle me null jayega to alag chheez ayegi
                                        // ! aur agar root jayega to alaga jab hum dubara add karne ke leye koshihs
                                        // karenge
    }

    private node implementation(node parent, int branch) {
        if (parent == null) {
            System.out.println("Enter the details of root node");
        } else {
            System.out.println("Enter the details of the " + parent.data + " and the branch is " + branch);
        }

        // ! Enter the data
        System.out.println("Enter the data for the node");

        // ! Node is created
        node nn = new node();

        // ! Value is taken from the user
        int num = sc.nextInt();

        // ! Value entered in the node
        nn.data = num;

        System.out.println("Enter the Number of children for the " + num);

        // ! Enter the size of the children
        int noc = sc.nextInt();

        // ! Calling the branches
        for (int i = 0; i < noc; i++) {
            node child = implementation(nn, i);
            nn.children.add(child);
        }
        return nn;
    }

    public void display() {
        System.out.println("--------------------");
        display(root);
        System.out.println("--------------------");
    }

    private void display(node NODE) {

        System.out.print(NODE.data + " -> ");

        for (node child : NODE.children) {
            System.out.print(child.data + " ");
        }
        System.out.println(".");

        for (node child : NODE.children) {
            display(child);
        }

        // ! MANUALLY
        /*
         * for(int i=0;i<root.children.size();i++)
         * {
         * display(root.children.get(i));
         * }
         * 
         * 
         * System.out.print(root.data+" -> ");
         * for(int i=0;i<root.children.size();i++)
         * {
         * System.out.print(root.children.get(i).data+" ");
         * }
         * System.out.println(". ");
         */

    }

    public int size() {
        int size = size(root);
        return size;
    }

    private int size(node NODE) {
        int size = 0;
        for (node child : NODE.children) {
            size = size + size(child);
        }

        return size + 1;
    }

    public boolean find(int val) {
        return find(root, val);
    }

    private boolean find(node root, int val) {
        if (val == root.data)
            return true;

        for (node child : root.children) {
            boolean flag = find(child, val);
            if (flag) {
                return true;
            }

            // ! OR
            // if(find(root, val))
            // return true;
        }

        return false;
    }

    public int findMax() {
        return findMax(root);
    }

    private int findMax(node root) {

        int max = Integer.MIN_VALUE;
        for (node child : root.children) {
            max = Math.max(findMax(child), max);
        }

        return Math.max(max, root.data);
    }

    public int height() {

        return height(root);
    }

    private int height(node root) {

        int max = -1;
        for (node child : root.children) {
            max = Math.max(height(child), max);
        }

        return max + 1;
    }

    public void mirror() {
        mirror(root);
    }

    private void mirror(node root) {

        // ! SP
        for (node child : root.children) {
            mirror(child);
        }

        // ! work
        int i = 0, j = root.children.size() - 1;
        while (i < j) {
            node temp = root.children.get(i);
            root.children.set(i, root.children.get(j));
            root.children.set(j, temp);

            i++;
            j--;
        }

    }

    public void level(int requiLevel) {
        level(0, requiLevel, root);
    }

    private void level(int currLevel, int requiLevel, node root) {

        if (currLevel == requiLevel) {
            System.out.print(root.data + " ");
            return;
        }

        for (node child : root.children) {
            level(currLevel + 1, requiLevel, child);
        }
    }

    public void linearize() {
        linearize(root);
    }

    private void linearize(node root) {

        for (node child : root.children) {
            linearize(child);
        }
        // self work

        while (root.children.size() > 1) {
            node child = root.children.remove(1);
            // get tail end point pata karle
            node tail = getTail(root.children.get(0));
            tail.children.add(child);
        }

    }

    private node getTail(node root) {
        if (root.children.size() == 0) {
            return root;
        }

        return getTail(root.children.get(0));
    }

    public void preorder() {
        preorder(root);
        System.out.println("-------------");
    }

    private void preorder(node root) {

        System.out.println("Preorder" + root.data);

        for (node child : root.children) {
            preorder(child);
        }
    }

    public void postorder() {
        postorder(root);
        System.out.println("-------------");
    }

    private void postorder(node root) {

        for (node child : root.children) {
            postorder(child);
        }

        System.out.println("postorder " + root.data);
    }

    public void levelorder() {
        Queue<node> q = new LinkedList<>(); // * Use the queue
        q.add(root);

        while (!q.isEmpty()) {
            for (node child : q.peek().children) {
                q.add(child);
            }
            node rem = q.remove();
            System.out.print(rem.data + " ");
        }
        System.out.println();

    }

    public void levelorder_lineBreak1() {
        Queue<node> pq = new LinkedList<>();
        Queue<node> hq = new LinkedList<>();

        pq.add(root);
        while (!pq.isEmpty()) {
            node temp = pq.remove();

            System.out.print(temp.data + " ");
            for (node child : temp.children) {
                hq.add(child);
            }

            if (pq.isEmpty()) {
                System.out.println();
                Queue<node> tempQueue = pq;
                pq = hq;
                hq = tempQueue;
            }
        }
        System.out.println();
    }

    public void levelOrder_lineBreak_2() {
        Queue<node> q = new LinkedList<>();

        q.add(root);
        q.add(null);

        while (true) {
            if (q.peek() == null) {
                System.out.println();
                q.remove();
                q.add(null);

                if (q.size() == 1) {
                    return;
                }
            }

            node temp = q.remove();
            System.out.print(temp.data + " ");

            for (node child : temp.children) {
                q.add(child);
            }

        }
    }

    public void zigzag() {
        Stack<node> ps = new Stack<>();
        Stack<node> hs = new Stack<>();
        int level = 0;

        ps.add(root);

        while (!ps.isEmpty()) {
            if (level % 2 == 0) {
                node temp = ps.pop();
                System.out.print(temp.data + " ");
                for (node child : temp.children) {
                    hs.push(child);
                }
            } else {
                node temp = ps.pop();
                System.out.print(temp.data + " ");
                for (int i = temp.children.size() - 1; i >= 0; i--) {
                    hs.push(temp.children.get(i));
                }
            }

            if (ps.isEmpty()) {
                System.out.println();
                level++;
                Stack<node> temp_s = ps;
                ps = hs;
                hs = temp_s;
            }
        }
    }

}
