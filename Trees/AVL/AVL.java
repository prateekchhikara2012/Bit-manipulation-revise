package Trees.AVL;


public class AVL {

    private class node
    {
        int data;
        int hi;
        node left;
        node right;
    }

    private node root=null;

    public void addNode(int val)
    {
        root=addNode(root, val);
    }
    private node addNode(node root , int val){
        if(root==null)
        {
            node nn=new node();
            nn.data=val;

            return nn;
        }


        if(val< root.data)
        {
            root.left=addNode(root.left , val);
        }
        else
        {
            root.right=addNode(root.right ,val);
        }

        //! self height calculation
        root.hi=Math.max(height(root.left) , height(root.right))+1;



        int bf=bf(root);


        //? LL CASE-1  -> single right rotation
        if(bf>=2 && val <root.left.data)
        {
           return LL(root);
        }


        //? RR CASE 2 -> single left rotation
        if(bf<=-2 && val >root.right.data )
        {
            return RR(root);
        }

        //? LR CASE 3 -> Solution is LR only
        if(bf>=2 && val> root.left.data)
        {
            node tempRoot=RR(root.left);

            root.left=tempRoot;

            return LL(root);
        }
        

        //? RL CASE 4 -> Solution is RL only
        if(bf<=-2 && val<root.right.data)
        {
            node tempRoot=LL(root.right);
            root.right=tempRoot;
            return RR(root);
        }

        return root;

    }


    private node LL(node root)
    {
        node a=root;

        //! getting nodes
        node b=a.left;
        node t3=b.right;

        //! linking
        b.right=a;
        a.left=t3;

        //! height calculation
        a.hi=Math.max(height(a.left) , height(a.right))+1;
        b.hi=Math.max(height(b.left) , height(b.right))+1;

        //! returning the node
        return b;
    }

    private node RR(node root)
    {
        node a= root;

        //! getting nodes
        node b=a.right;
        node t2=b.left;

        //! linking
        b.left=a;
        a.right=t2;

        //! height calculation
        a.hi=Math.max(height(a.left) , height(b.right))+1;
        b.hi=Math.max(height(b.left) , height(b.right))+1;

        //! returning the node
        return b;
    }

    private int height(node root)
    {
        if(root==null)
        {
            return -1; 
        }
        else
        {
            return root.hi;
        }
    }
    private int bf(node root)
    {
        int left=height(root.left );
        int right=height(root.right);
        return left-right;
    }
    


   public void display() {

		System.out.println("-----------------------");
		display(root);
		System.out.println("-----------------------");
	}

	private void display(node node) {

		if (node == null)
			return;

		String str = "";

		if (node.left == null)
			str += ".";
		else
			str += node.left.data;

		str += " -> " + node.data + " <- ";

		if (node.right == null)
			str += ".";
		else
			str += node.right.data;

		System.out.println(str);

		display(node.left);
		display(node.right);

	}
}
