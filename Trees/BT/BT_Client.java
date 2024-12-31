package Trees.BT;

public class BT_Client {
    
    public static void main(String[] args) {

        // 10 true 20 true 50 false false true 60 false false true 30 false true 70 true 80 true 90 false false false false

        //! 1 true  -2 true  4 false false true  5 false false true 3 true -6 false false true 2  false false

       
        BinaryTree bt=new BinaryTree();

        // System.out.println(bt.find(10) );
        // System.out.println(bt.max());
        // System.out.println(bt.height());
        // System.out.println(bt.countLeafNode());

        //! Level order zig zag 
        bt.levelorderZigZagLinkedList();
        

        // ! DIAMETER
        // 10 true 20 true 30 true 40 true 60 true 80 false false false false true 50 false true 70 false true 90 false false false true 34 true 23 false false false
        // bt.diameter();


        //! BALACNDED 
         //bt.isBalanced();


        //! FLIPPING
        // 10 true 20 true 40 false false true 50 false false true 30 false true 70 false false
        // 10 true 30 true 70 false false false true 20 true 50 false false true 40 false false 
        
        
        // BinaryTree bt_1=new BinaryTree();
        // bt.flipping(bt_1);
        // bt.display();
        // bt_1.display();


        //! Itratively Pre-order ,Post-order , In-order
        // bt.preorderI();

        //! max Subtree-sum
        // System.out.println(bt.sms());

        //! vertical order

        // 10 true 20 true 40 true 170 false false true 80 false false true 50 false false true 30 true 60 true 90 false false true 100 false false true 70 false false
        // bt.verticalOrder();
        // bt.verticalOrderR();


        //! creations of tree
        
        // int [] pre={10,20,40,50,70,30,60};
        // int [] in={40,20,50,70,10,60,30};
        // // int [] post= {40,70,50,20,60,30,10};

        // int [] pre={10,20,40,50,80,90,30,60,70};
        // int [] post={40,80,90,50,20,60,70,30,10};


        // BinaryTree bt_P_I=new BinaryTree(pre , in ,post );
        // bt_P_I.display();


        // bt.isBst();

        int arr[]={10,20,30,40,50,-1,60,-1,-1,-1,-1,-1,-1};

        bt.LevelOrderImplementation(arr);
        bt.display();
    } 
}