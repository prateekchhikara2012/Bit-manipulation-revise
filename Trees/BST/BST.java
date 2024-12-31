package Trees.BST;



public class BST {

    private class node{
        int data;
        node left;
        node right;
    }

    private node root=null;

    BST(int [] inorder)
    {
        root=construction(inorder, 0, inorder.length-1, root);
    }
    private node construction(int [] inorder , int lo , int hi ,node parent)
    {
        if(lo>hi)
        {
            return null ;
        }

        if(parent==null)
        {
            System.out.println("Enter the data for the root node");
        }
        else
        {
            System.out.println("Enter the data for the "+parent.data+" node");
        }


        int mid=(lo+hi)/2;
        node nn=new node();
        nn.data=inorder[mid];

        nn.left=construction(inorder, lo, mid-1, nn);
        nn.right=construction(inorder, mid+1, hi, nn);

        return nn;
    }

    //! Q1) DISPLAY
    public void display()
    {
        System.out.println("------");
        display(root);
        System.out.println("------");
    }
    private void display(node root)
    {

        if(root==null)
        {
            return ;
        }

        String ans=" -> "+root.data+" <- ";

        if(root.left==null)
        {
            ans="."+ans;
        }
        else
        {
            ans=root.left.data+ans;
        }

        if(root.right==null)
        {
            ans=ans+".";
        }
        else
        {
            ans=ans+root.right.data;
        }

        System.out.println(ans);

        display(root.left);
        display(root.right);  
    }

    //! Q2) FIND MAX
    public int findMax()
    {
        return findMax(root);
    }
    private int findMax(node root)
    {
        if(root.right==null)
        {
            return root.data;
        }
        return findMax(root.right);
    }

    //! Q3) FIND VALUE
    public boolean find(int val)
    {
        return find(root, val);
    }
    private boolean find(node root , int val)
    {
        if(root==null)
        {
            return false;
        }

        if(root.data<val)
        {
            return find(root.right, val);
        }
        else if(root.data>val)
        {
            return find(root.left, val);
        }
        else
        {
            return true;
        }
    }

    //! Q4) REPLACE WITH SUM LARGER
    public void replaceWithSumLarger()
    {
        replaceWithSumLarger(root);
    }
    private int sum=0;
    private void replaceWithSumLarger(node root) {

        if(root==null)
        {
            return ;
        }

        replaceWithSumLarger(root.right);

        int temp=root.data;
        root.data=sum;
        sum+=temp;

        replaceWithSumLarger(root.left);
    }

    //* IMPORTANT RETURNING */
    // private int fun(node root , int fsum)
    // {
    //     if(root==null)
    //     {
    //         return fsum;
    //     }
    //     int right=fun(root.right , fsum);
    //     int sum=right+root.data;
    //     root.data=sum;
        
    //     return fun(root.left,sum);
    // }


    //! Q5) PRINT IN RANGE
    public void printInRange(int left ,int right)
    {
        printInRange(left, right, root);
    }
    private void printInRange(int l , int r, node root)
    {
        if(root==null)
        {
            return ;
        }

        if(root.data >= l && root.data <=r)   //! -- IN range
        {
            //?  2 calls will be placed and you have to print in the ascending manner so apply ( LNR ) - As it will give me in the sorted way 
            
            //  left call 
            printInRange(l, r , root.left);
            
            // work -- //! It will give me in the sorted manner 
            System.out.print(root.data+" ,");

            // right call
            printInRange(l ,r ,root.right);
        }
        else if(root.data<l)
        {
            //? Apply only the right call
            printInRange(l ,r ,root.right);
        }
        else if(root.data>r)
        {
            //? Apply only the left call
            printInRange(l ,r, root.left);
        }
    }

    //! Q6) ADD A DESRIED NODE
    //! A1)
    public void addnodeA1(int val)
    {
        if(root==null)
        {
            node nn=new node();
            nn.data=val;
            root=nn;
            return ;
        }
        addnodeA1(root, val);
    }
    private void addnodeA1(node root , int val)
    {
        if(val<root.data)
        {
            if(root.left==null)
            {
                node nn=new node();
                nn.data=val;
                root.left=nn;
                return ;
            }
            addnodeA1(root.left, val);
        }
        else
        {
            if(root.right==null)
            {
                node nn=new node();
                nn.data=val;
                root.right=nn;
                return ;
            }
            addnodeA1(root.right, val);
        }
    }

    //! A2)
    public void addnodeA2(int val)
    {
        if(root==null)
        {
            node nn=new node();
            nn.data=val;
            root=nn;
            return ;
        }

        addnodeA2(null , root, val);
    }
    private void addnodeA2(node parent , node root , int val)
    {
        if(root==null)
        {
            node nn=new node();
            nn.data=val;

            if(val<parent.data)
            {
                parent.left=nn;
            }
            else
            {
                parent.right=nn;
            }

            return  ;
        }

        if(val<root.data)
        {
            addnodeA2(root, root.left, val);
        }
        else
        {
            addnodeA2(root , root.right ,val);
        }
    }

    //! A3)
    public void addnodeA3(int val)
    {
        if(root==null)
        {
            node nn=new node();
            nn.data=val;
            root=nn;
            return ;
        }
        addnodeA3(root, val);
    }
    private node addnodeA3(node root , int val)
    {
        if(root==null)
        {
            node nn=new node();
            nn.data=val;
            return nn;
        }


        if(val<root.data)
        {
            root.left=addnodeA3(root.left, val);
        }
        else
        {
            root.right=addnodeA3(root.right, val);
        }
        return root;
    }
    

    //! Remove NOde
    public void removenode(int val)
    {
        if(root==null)
        return ;

        if(val==root.data){
            if(root.left==null && root.right==null){
                root=null;
            }
            else if(root.left!=null && root.right==null){
                root=root.left;
            }   
            else if(root.left==null && root.right!=null){
                root=root.right;
            }
            else{
                removenode(root, null, val);
            }
            
            return ;
        }

        removenode(root, null, val);


    }
    private void removenode(node root , node parent, int val)
    {
        if(root==null)
        {
            return ;
        }

        if(val<root.data)
        {
            removenode(root.left,root, val);
        }
        else if(val>root.data)
        {
            removenode(root.right,root, val);
        }
        else
        {

            //! Case 1 : If both the left and right are null  Meaning (Leaf node)
            if(root.left==null && root.right==null){
                if(val>parent.data){
                    parent.right=null;
                }
                else{
                    parent.left=null;
                }
            }

            //! Case 2 : If left is not null and the right is null
            else if(root.left!=null  && root.right==null)
            {
                if(root.data<parent.data){
                    parent.left=root.left;
                }
                else{
                    parent.right=root.left;
                }
            }

            //! Case 3 : If left is null and right is not null
            else if(root.left==null && root.right!=null)
            {
                if(root.data<parent.data){
                    parent.left=root.right;
                }
                else
                {
                    parent.right=root.right;
                }
            }

            //! Case 4: Internal node which is having both the left and the right child
            else
            {
                // either the lst max or rst min
                // 1 lst max
                
                int lstMax=findMax(root.left);

                removenode(root.left, root, lstMax);
                root.data=lstMax;
            }
        }
    }

}
 