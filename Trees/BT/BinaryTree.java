package Trees.BT;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class BinaryTree {
       
    private Scanner sc=new Scanner(System.in);

    private class node
    {
        int data ; 
        node left; 
        node right; 
    }
    private node root =null ;

    public BinaryTree ()
    {
         root= implementation(root , false);
    }
    
    private node implementation(node root , boolean flag)
    {
        if( root==null)
        {
            System.out.println("Enter the details for the root node");
        }
        else
        {
            System.out.println("Enter the details for the "+root.data+" node with the call of "+flag);
        }

        System.out.println("Enter the data");

        node nn =new node();
        nn.data=sc.nextInt();

        System.out.println("Do you want the left child of the "+ nn.data);
        boolean left= sc.nextBoolean();

        if(left)
        {
            node temp=implementation(nn, false);
            nn.left=temp;
        }

        System.out.println("Do you want the right Child of the "+ nn.data);

        boolean right=sc.nextBoolean();


        if(right)
        {
            node temp=implementation(nn, true);
            nn.right=temp;
        }

        return nn;
    }

    public void display()
    {
        System.out.println("---");
        display(root);
        System.out.println("---");
    } 
    private void display(node root)
    {
        if(root==null)
        return  ;

        String str="";
        str=" -> "+root.data+" <- ";

        if(root.left!=null)
        {
            str=root.left.data+str;
        }
        else
        {
            str="."+str;
        }

        if(root.right!=null)
        {
            str=str+root.right.data;
        }
        else
        {
            str=str+".";
        }


        System.out.println(str);

        //left all
        display(root.left);
        display(root.right);


    }

    public int size()
    {
        return size(root);
    }
    private int size(node root)
    {
        if(root==null)
        return 0;

        int left=size(root.left);
        int right=size(root.right);

        return left+right+1;
    }

    public int max()
    {
        return max(root);
    }
    private int max(node root)
    {
        if(root==null)
        {
            return Integer.MIN_VALUE;
        }

        int x=max(root.left);
        int y=max(root.right);

        int max=Math.max(x,y);
        return Math.max(max , root.data);

        //!  ----------- OR -----------   FOR THE SIMPLIFICATION 
        // int max= Math.max(max(root.left),max(root.right) );
        // return Math.max(max, root.data);

    }

    public int min()
    {
        return min(root);
    }
    private int min(node root)
    {
        if(root==null)
        {
            return Integer.MAX_VALUE;
        }

        int left=min(root.left);
        int right=min(root.right);
        return Math.min(root.data , Math.min(left , right));
        
    }
    public boolean find(int item)
    {
        return find(root , item );
    }
    private boolean find(node root , int item)
    {

        if(root==null)
        {
            return false;
        }

        if(root.data==item)
        {
            return true;
        }



        if(find(root.left , item) || find(root.right ,item))
        {
            return true;
        }
        else 
        {
            return false;
        }


    }

    public int height()
    {
        return height(root);
    }
    private int height(node root)
    {
        if(root==null)
        {
            return -1 ;
        }
        int left=height(root.left);
        int right=height(root.right);

        return Math.max(left ,right)+1;

    }

    public int countLeafNode()
    {
        return countLeafNode(root);
    }
    private int countLeafNode(node root)
    {
        if(root==null)
        {
            return 0;
        }
        if(root.left==null && root.right==null)
        {
            return 1; 
        }


        int x=countLeafNode(root.left);
        int y=countLeafNode(root.right);
        return x+y;
    }


    public void levelorderZigZagLinkedList()
    {
        List<List<Integer>> mainList=new ArrayList<>();
        List<Integer> list=new ArrayList<> ();


        LinkedList <node> pl=new LinkedList<>();
        LinkedList <node> hl=new LinkedList<>();

        pl.add(root);
        int i=0;

        while(!pl.isEmpty())
        {
            if(i%2==0)
            {
                // list me add karna hai

                node temp=pl.removeFirst();
                list.add(temp.data);
                
                if(temp.left!=null)
                {
                    hl.addLast(temp.left);
                }

                if(temp.right!=null)
                {
                    hl.addLast(temp.right);
                }
            }
            else
            {
                node temp=pl.removeLast();
                // add kia 40

                list.add(temp.data);
                if(temp.right!=null)
                {
                    hl.addFirst(temp.right);
                }

                if(temp.left!=null)
                {
                    hl.addFirst(temp.left);
                }
            }


            if(pl.isEmpty())
            {
                i++;

                mainList.add(list);
                list=new ArrayList<>();

                LinkedList <node> temp=pl;
                pl=hl;
                hl=temp;

            }
        }

        System.out.println(mainList);



    }
    
    //! Diameter
    private int diameter=-1;
    public void diameter()
    {
        // complexity is o(N2) for this
        //! 1
        diameter1(root);
        // System.out.println(diameter);

        //! 2
         System.out.println(diameter2(root));

        //! 3
        diameter3(root);
        // System.out.println(diameter);

        //! 4
        diaM dm=diameter4(root);
        System.out.println(dm.dia);

    }

    private void diameter1(node root)
    {
        if(root==null)
        {
            return ;
        }
        diameter1(root.left);
        diameter1(root.right);

        int x=height(root.left);
        int y=height(root.right);

        int ans=x+y+2;
        if(ans>diameter)
        {
            diameter=ans;
        }
    }
    private int diameter2(node root)
    {
        if(root==null)
        {
            return 0; 
        }
        int lsd=diameter2(root.left);
        int rsd=diameter2(root.right);

        int lst=height(root.left);
        int rst=height(root.right);

        int sum=lst+rst+2;

        return Math.max( sum ,Math.max(lsd,rsd));
    }
    private int diameter3(node root)
    {
        // height leke atta hun sath me 

        if(root==null)
        {
            return -1; 
        }

        int lst=diameter3(root.left);
        int rst=diameter3(root.right);

        int sum= lst + rst+2 ; 
        if(sum > diameter)
        {
            diameter=sum ; 
        }

        return Math.max(lst ,rst )+1;
    }
            
    private diaM diameter4(node root)
    {


        if(root==null)
        {
            return  new diaM() ;
        }
        diaM LSP=diameter4(root.left);
        diaM RSP=diameter4(root.right);

        // self work 
        diaM dm=new diaM();

        //! finding out the height 
        dm.hi=Math.max(LSP.hi , RSP.hi) +1;

        //! self diameter
        int sd=LSP.hi +RSP.hi + 2;
        dm.dia= Math.max(sd , Math.max(LSP.dia , RSP.dia));

        return dm;
    }
    private class diaM
    {
        int dia=0;
        int hi=-1;
    }


    //! Is balanced
    public void isBalanced()
    {
        balanced(root);
        System.out.println(isBalanced);
       System.out.println( balanced2(root).isBalanced);

    }
    private boolean isBalanced=true;
    private int balanced(node root)
    {
        if(root==null)
        {
            return -1 ;
        }
        int lsh=balanced(root.left);
        int rsh=balanced(root.right);

        int height=Math.max(lsh , rsh)+1;

        if(isBalanced)
        {
            int bf=lsh-rsh;
            if(bf==0 || bf==1 || bf==-1);
            else
            {
                isBalanced=false;
            }
        }

        return height;

    }

    private class bal
    {
        boolean isBalanced =true ; 
        int  height=-1;
    }
    private bal balanced2(node root)
    {
        if(root==null)
        {
            return new bal();
        }

        bal lst=balanced2(root.left);
        bal rst=balanced2(root.right);

        int selfHeight=Math.max(lst.height , rst.height)+1;

        bal nb=new bal();
        nb.height=selfHeight;

        if(lst.isBalanced && rst.isBalanced)
        {
            int bf=lst.height - rst.height;
            if(bf==0 || bf==1 || bf==-1);
            else
            {
                nb.isBalanced=false;
            }
        }
        else
        {
            nb.isBalanced=false;
        }

        return nb;
    }


    //! flipping
    public void flipping(BinaryTree bt)
    {
        System.out.println(flipping(root , bt.root) );
    }
    private boolean flipping(node root ,node root_1)
    {
        if(root==null && root_1==null)
        {
            return true ;
        }
        else if(root==null || root_1==null)
        {
            return false;
        }

        if(root.data==root_1.data)
        {
            boolean noFlip= (flipping(root.left,root_1.left) && flipping(root.right ,root_1.right));

            boolean Flip  = (flipping(root.left,root_1.right) && flipping(root.right ,root_1.left));

            //! for flipping the tree 
            // if(Flip)
            // {
            //     node temp=root_1.left;
            //     root_1.left=root_1.right;
            //     root_1.right=temp;
            // }

            return noFlip || Flip;
        }
        else
        {
            return false;
        }

    }
    

    //! Itratively Pre-order ,Post-order , In-order
    // NLR : preorder
	// LRN : postorder
	// LNR : inorder
	// NRL : rev postorder
	// RLN : rev preorder
	// RNL : rev inorder
    private class pair
    {
        node addr;
        boolean sd ; 
        boolean lc;
        boolean rc;
    }
    public void preorderI()
    {
        Stack < pair > s=new Stack <> ();
        
        // initally -- put the root node at stack 
        pair init=new pair();
        init.addr=root;
        s.push(init);

        while(!s.isEmpty())
        {

            pair peek =s.peek();

            if(peek.addr==null)
            {
                s.pop();

                continue ;     //! very imp step  because the peek is still pointing to the null
            }



            if(peek.sd==false)
            {
                System.out.print(peek.addr.data +" ") ;
                peek.sd=true;

            }
            else if(peek.lc==false)
            {
                peek.lc=true;

                pair np=new pair();
                np.addr=peek.addr.left;

                s.push(np);
            }
            else if(peek.rc==false)
            {
                peek.rc=true;
                
                pair np=new pair();
                np.addr=peek.addr.right;

                s.push(np);
            }
            else
            {
                s.pop();
            }

        }
    }



    //! Subtree max-sum
    private int max=  Integer.MIN_VALUE;
    public int sms()
    {
        sms1(root);
        System.out.println(max);

        smsPair np=sms2(root);
        return np.ms;
    }
    private int sms1(node root)
    {
        if(root==null)
        {
            return 0;
        }

        int lc=sms1(root.left);
        int rc=sms1(root.right);

        int sum=lc+rc+root.data;

        if(sum>max)
        {
            max=sum;
        }

        return sum;
    }

    private smsPair sms2(node root)
    {
        if(root==null)
        {
            return new smsPair();
        }
        smsPair lc= sms2(root.left);
        smsPair  rc=sms2(root.right);

        smsPair np=new smsPair();
        np.ts=lc.ts+rc.ts+root.data;
        np.ms=Math.max( np.ts,Math.max(lc.ms , rc.ms));

        return np;
    }
    private class smsPair
    {
        int ts=0;
        int ms=Integer.MIN_VALUE;       // for teh negative test cases
    }



    //! Vertical order
    private class vPair
    {
        node addr;
        int count;
    }
    public void verticalOrder()
    {
        Queue < vPair > q=new LinkedList<>();
        
        vPair vp=new vPair();
        vp.count=0;
        vp.addr=root;

        q.add(vp);
        HashMap < Integer, ArrayList<Integer > > map =new HashMap<>() ;

        while(!q.isEmpty())
        {
            //! 1 Remove from map
            vPair rp=q.remove();

            //! 2. Check weather it is present or not
            if(map.containsKey(rp.count))
            {
                // agar contina karta hai to simply 
                ArrayList <Integer> list= map.get(rp.count);
                list.add(rp.addr.data);  
            }
            else  
            {
                ArrayList < Integer> list=new ArrayList<>();
                list.add(rp.addr.data);
                map.put(rp.count , list);
            }

            //! 3 Child add
            if(rp.addr.left!=null)
            {
                vPair lnp=new vPair();
                lnp.count=rp.count-1;
                lnp.addr=rp.addr.left;
                q.add(lnp);
            }
            if(rp.addr.right!=null)
            {
                vPair rnp=new vPair();
                rnp.count=rp.count+1;
                rnp.addr=rp.addr.right;
                q.add(rnp);
            }
        }

        //! intially the hashmap will store in the different form that is jumble way
        //! so to avoid that sort the keys of the hashmap 

        ArrayList <Integer> keys=new ArrayList<>(map.keySet());
        Collections.sort(keys);

        for(int i=0;i<keys.size();i++)
        {
            System.out.println(keys.get(i)+" -> "+map.get(keys.get(i)));
        }
    }


    private class vPairR
    {
        int data;
        int level;
    }  
    public void verticalOrderR()
    {
        HashMap <Integer , ArrayList <vPairR> > map=new HashMap<>() ;
      
        verticalOrderR(0, 0, map, root);

        //! converting into sorted form
        ArrayList<Integer> keys=new ArrayList<>(map.keySet());
        Collections.sort(keys);

        System.out.println();
        for(int i=0;i<keys.size() ;i++)
        {
            ArrayList< vPairR > list=map.get(keys.get(i));

            for(int j=0;j<list.size() ;j++)
            {
                for(int k=j+1; k<list.size() ;k++)
                {
                    if(list.get(j).level > list.get(k).level)
                    {
                        vPairR temp=list.get(j);
                        list.set(j ,list.get(k));
                        list.set(k , temp);
                    }
                }
            }

            System.out.print(keys.get(i)+" -> ");
            for(int j=0 ; j<list.size() ; j++)
            {
                System.out.print(list.get(j).data+" ");
            }
            System.out.println();
        }
    }
    private void verticalOrderR(int level , int count , HashMap <Integer  , ArrayList <vPairR> > map , node root)
    {
        if(root==null)
        {
            return  ;
        }

        if(map.containsKey(count))
        {
            vPairR pair=new vPairR();
            pair.data=root.data;
            pair.level=level;
            map.get(count).add(pair);
        }
        else
        {
            ArrayList <vPairR > list=new ArrayList<>();
            vPairR pair=new vPairR();
            pair.data=root.data;
            pair.level=level;
            list.add(pair);
            map.put(count, list);
        }


        verticalOrderR(level+1, count-1, map ,root.left);
        verticalOrderR(level+1, count+1, map, root.right );
    }

  

    //! Creation of Tree from pre and inorder
    public BinaryTree (int [] pre , int [] in  ,int  [] post )   // ? Constructor
    {
        //* using the pre and the inorder
        root=creationOfTreeFrom_Pre_Ino(pre, 0, pre.length-1, in, 0, in.length-1);

        //* using the post and the inorder
        root=creationOfTreeFrom_Post_Ino(post, 0, post.length-1, in, 0, in.length-1);

        //* using the pre and the post order
        root=creationOfTreeFrom_Pre_Post(pre, 0, pre.length-1, post, 0, post.length-1);
    }

    private node creationOfTreeFrom_Pre_Ino(int [] pre , int pLo , int pHi , int [] in , int iLo , int iHi ){

        // ! base-case

        if(pLo>pHi || iLo>iHi)
        {
            return null;
        }

        //! WORK
        node nn=new node();
        nn.data=pre[pLo];


        // todo : finding the  nn.data  element in the inorder array
        int pp=0; // pp ->  partition point
        for(int i = iLo ; i <= iHi ; i++)
        {
            if(in[i]==nn.data)
            {
                break;
            }
            pp++;
        }

        //! SP 
        node left=creationOfTreeFrom_Pre_Ino(pre, pLo+1, pLo+pp, in, iLo, iLo+pp-1); 
        node right=creationOfTreeFrom_Pre_Ino(pre, pLo+1+pp, pHi, in, iLo+pp+1, iHi);

        nn.left=left;
        nn.right=right;


        return nn;

    }

    private node creationOfTreeFrom_Post_Ino(int [] post , int pLo , int pHi , int []in , int iLo , int iHi){

        if(pLo>pHi || iLo>iHi)
        {
            return null;
        }

        node nn =new node();
        nn.data =post[pHi];

        int pp =0; 
        for(int i=iLo ; i<= iHi;i++)
        {
            if(in[i]==post[pHi])
            {
                break ;
            }

            pp++;
        }

        node left=creationOfTreeFrom_Post_Ino(post, pLo, pLo+pp-1, in, iLo, iHi+pp-1);
        node right=creationOfTreeFrom_Post_Ino(post, pLo+pp, pHi-1, in, iLo+pp+1, iHi);

        nn.left=left;
        nn.right=right;

        return nn;

    }

    private node creationOfTreeFrom_Pre_Post(int [] pre , int preLo , int preHi , int [] post , int postLo , int postHi)
    {

        if(preLo==preHi && postLo==postHi)
        {
            node nn=new node();
            nn.data=pre[preLo];
            return nn;
        }

        node nn=new node();
        nn.data=pre[preLo];

        int te=pre[preLo+1];

        int pp=0;
        for(int i=postLo;i<=postHi;i++)
        {
            if(post[i]==te)
            {
                break;
            }
            pp++;
        }


        node left=creationOfTreeFrom_Pre_Post(pre, preLo+1, preLo+1+pp, post, postLo, postLo+pp);
        node right=creationOfTreeFrom_Pre_Post(pre, preLo+pp+2, preHi, post, postLo+pp+1, postHi-1);

        nn.left=left;
        nn.right=right;

        return nn;
    }

    public void isBst()
    {

        isBst(root);
        System.out.println(isBst);


        System.out.println(isBst2(root) );

        System.out.println(isBst3(root).flag);
    }

    boolean isBst=true;
    private void isBst(node root)
    {
        if(root==null)
        {
            return ;
        }

        isBst(root.left);
        isBst(root.right);


        int lstMax=max(root.left);
        int rstMin=min(root.right);


        if(root.data>lstMax && root.data<rstMin);
        else
        {
            isBst=false;
        }

    }

    private boolean isBst2(node root ){
        if(root==null)
        {
            return true;
        }

        boolean left=isBst2(root.left);
        boolean right=isBst2(root.right);

        // lst max
        if(left && right)
        {
            int lstMax=max(root.left);
            int rstMin=min(root.right);

            if(root.data>lstMax && root.data<rstMin)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }


    private class isBstPair{
        boolean flag=true;
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
    }
    private isBstPair isBst3(node root){

        if(root==null){
            return new isBstPair() ;
        }

        isBstPair left=isBst3(root.left);
        isBstPair right=isBst3(root.right);

        isBstPair sp=new isBstPair();
        if(left.flag && right.flag)
        {
            if(left.max<root.data && root.data <right.min)
            {
                sp.max=Math.max(root.data , Math.max(left.max , right.max));
                sp.min=Math.min(root.data , Math.min(left.min , right.min));
            }
            else
            {
                sp.flag=false;
            }
        }
        else
        {
            sp.flag=false;
        }

        return sp;
    }

    private class lBstPair
    {
        boolean isBst=true;
        int max=Integer.MIN_VALUE;
        int min =Integer.MAX_VALUE;

        int size=0;
        int ln;
    }



    public void largestBST()
    {
        largestBST(root);
    }
    private lBstPair largestBST(node root)
    {
        if(root==null)
        {
            return new lBstPair();
        }

        lBstPair left=largestBST(root.left);
        lBstPair right=largestBST(root.right);
        
        lBstPair np=new lBstPair();

        if(left.isBst && right.isBst && left.max<root.data && root.data<right.min)
        {
            // ye bhe ek bst hai
        
            np.size=left.size+right.size+1;
            np.ln=root.data;
        }
        else
        {
            np.isBst=false;
            if(left.size>right.size)
            {
                np.ln=left.ln;
                np.size=left.size;
            }
            else
            {
                np.ln=right.ln;
                np.size=right.size;
            }
        }

        np.max=Math.max(root.data ,Math.max(left.max , right.max));
        np.min=Math.min(root.data ,Math.min(left.min , right.min));


        return np;
    }


    public void LevelOrderImplementation( int [] arr)
    {
        Queue < node> q=new LinkedList<>();
        node rn=new node();
        rn.data=arr[0];

        root=rn;
        q.add(rn);
        int i=1;

        while(!q.isEmpty())
        {
            node temp=q.remove();

            if(arr[i]!=-1)
            {
                node left=new node();
                left.data=arr[i];
                temp.left=left;
                q.add(left);
                
            }
            i++;

            if(arr[i]!=-1)
            {
                node right=new node();
                right.data=arr[i];
                temp.right=right;
                q.add(right);
            }
            i++;
        }
    }


    public void fun(int [] arr){

        Queue<node> pq=new LinkedList<>();

        int i=1;

        node nn=new node();
        nn.data=arr[0];
        root=nn;


        while(!pq.isEmpty())
        {
            node rm =pq.remove();
            if(arr[i]!=-1)
            {
                nn=new node();
                nn.data=arr[i];
                rm.left=nn;

                pq.add(nn);
            }
            i++;
            if(arr[i]!=-1)
            {
                nn=new node();
                nn.data=arr[i];
                rm.right=nn;
                pq.add(nn);
            }
            i++;
        }


    }

}
