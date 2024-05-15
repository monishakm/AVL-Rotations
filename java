import java.util.*;
public class Main {
    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    BST b=new BST();
 //   for(int i=0;i<5;i++)
    b.insert(7);
    b.insert(9);
    b.insert(8);
    b.insert(6);
    b.insert(1);
  
  }
}
class BST{
  node root;
  //node structure
  class node{
    int data,height;
    node left,right;
    //node class constructor to assign values to node properties
    node(int val){
      data=val;height=0;
      left=right=null;
    }
  }
  //method to get height of a node
  int get(node r){
    if(r==null)
    return -1;
    return r.height;
  }
  //method to invoke value insert method
   void insert(int val){
     root=ins(root,val);
   }
   //node insertion method
   node ins(node root,int key){
   //creating new node
     if(root==null){
       root=new node(key);
       return root;
     }
     //insert right
     if(key>root.data){
       root.right=ins(root.right,key);
     }
     //insert left
     else if(key<root.data)
     root.left=ins(root.left,key);
     //equal case
     else return root;
     //updating the height of each roots
     root.height=1+Math.max(get(root.left),get(root.right));
     System.out.println("the height :"+root.data+" is "+root.height);
     //balance factor finding for rotation
     int bfactor=findb(root);
      System.out.println("the Factor :"+root.data+" is "+bfactor);
     //LL condition
     if(bfactor>1&&key<root.left.data)
    return rotateRight(root);
    //LR condition
    if(bfactor>1&&key>root.left.data)
    {
      root.left=rotateLeft(root.left);
      return rotateRight(root);
    }
    //rr condition
    if(bfactor<-1&&key>root.right.data)
    return rotateLeft(root);
    //rl condition
    if(bfactor<-1&&key<root.right.data)
      { root.right=rotateRight(root.right);
      return rotateLeft(root);}
     return root;
   }
   //method Left rotation
   node rotateRight(node z){
     //storing the root left value in a node
     node y=z.left;
     //storing the root right value in a node
     node temp=y.right;
      //restoring the original root to its left child's right
       y.right=z;
        //restoring the right child of the node to original
       //root's left side
       z.left=temp;
        //updating original root height
       z.height=1+Math.max(get(z.left),get(z.right));
        //updating new root height
       y.height=1+Math.max(get(y.left),get(y.right));
        //returning new root for root updation   
       return y;
   }
   //method Right rotation
   node rotateLeft(node z){
     //storing the root right value in a node
     node y=z.right;
     
     //storing the root left value in a node
     node t1=y.left;
     //restoring the original root to its left child's left
       y.left=z;
       //restoring the left child of the node to original
       //root's right side
       z.right=t1;
       //updating original root height
       z.height=1+Math.max(get(z.left),get(z.right));
       //updating new root height
       y.height=1+Math.max(get(y.left),get(y.right));
     //returning new root for root updation      
       return y;
   }
   //method to find the balance factor
   int findb(node root){
     return get(root.left)-get(root.right);
   }
}
