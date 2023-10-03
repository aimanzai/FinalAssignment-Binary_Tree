package discrete_math;
import java.util.Scanner;

public class Assignment_3{
    public static void main(String[] args) {
        int[] index = getTree();
        printOutput(index, 0);  
    }
    public static int[] getTree(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a sequence of integer nodes: ");
        String num = input.nextLine();
        String[] element = num.split(" ");
        
        int[] index = new int[element.length];
        for(int i = 0; i < element.length; i++){
            if(element[i].equals("-"))
                index[i]= -1;
            else
                index[i] = Integer.parseInt(element[i]);
        }
        
        return index;
    }
    public static void printOutput(int[] index, int root){
        System.out.println();
        if(index[0] == -1)
            System.out.println("Root: -");
        else
            System.out.println("Root: " + index[0]);
        System.out.println("Height: " + getHeight(index, root));
        System.out.println("Number of Nodes: " + countNodes(index));
        System.out.println("Number of Leaves: " + calcLeaves(index, root));
        if(checkComplete(index, 0))
             System.out.println("Complete binary Tree: Yes");
        else
            System.out.println("Complete binary Tree: No");
       
        if(checkFull(index, 0))
             System.out.println("Full binary Tree: Yes");
        else
            System.out.println("Full binary Tree: No");
        System.out.print("Pre-order: "); preOrderTraversal(index, root); System.out.println();
        System.out.print("In-order: "); inOrderTraversal(index, root); System.out.println();
        System.out.print("Post-order: "); postOrderTraversal(index, root); System.out.println();
        System.out.println();
    }
    public static int leftChild(int n){
        return (2 * n) + 1;
    }
    
    public static int rightChild(int n){
        return (2 * n) + 2;
    }
    public static int getHeight(int[] tree, int index){
        int height = -1;
        int levelSize = 1;

        while(index < tree.length && levelSize > 0){
            for(int i = 0; i < levelSize; i++) {
                int currentNodeIndex = index + i;

                if(currentNodeIndex < tree.length && tree[currentNodeIndex] != -1){
                    int leftChildIndex = (2 * currentNodeIndex) + 1;
                    int rightChildIndex = (2 * currentNodeIndex) + 2;
                if (leftChildIndex < tree.length)
                    levelSize++;
                if (rightChildIndex < tree.length)
                    levelSize++;
                }
            }

            levelSize /= 2;
            height++;
            index = (2 * index) + 1;
        }   
        return height;
    }
    public static int countNodes(int[] index){
        int count = 0;
        for(int i = 0; i < index.length; i++){
            int node = index[i]; //Store one by one the value of the index in node
            if(node != -1) //If the index is not equal -1 (which is null) 
                count++;
        }
        return count;
    }
    public static int calcLeaves(int[] index, int root){    
        int left = leftChild(root), right = rightChild(root);
        
        //To check whether the root of the tree is " - ", if it is then it will return 0
        if(root >= index.length || index[root] == -1)
            return 0;
        
        //To check the condition if each left & right nodes have their own offspring & if not it will return 1 
        if((left >= index.length || index[left] == -1) && (right >= index.length || index[right] == -1))
            return 1;
        
        //In here we will pass the value of the condition that we check earlier and calculate it
        return calcLeaves(index, left) + calcLeaves(index, right);
    }
    public static boolean checkComplete(int[] index, int root){
        int left = leftChild(root), right = rightChild(root);
        boolean flag = false;
        
        //To check whether the root is out of bounds and index[root] = " - ", it will return false
        if(root >= index.length || index[root] == -1)
            return flag;
        
        //To check if the left child is out of bounds and if left child is " - " , it will return false
        if(left < index.length && index[left] == -1)
            return flag;
        
        return checkComplete(index, left) && checkComplete(index, right);
    }
    public static boolean checkFull(int[] index, int root){
       int left = leftChild(root), right = rightChild(root);
       boolean flag = true;
       
       //To check whether the root is out of bounds and index[root] equal " - ", it will return false
       if(root >= index.length || index[root] == -1) 
           return !flag;
       
       //To check the condition if each left & right nodes have their own offspring & if not it will return true
       if((left >= index.length || index[left] == -1) && (right >= index.length || index[right] == -1)) 
           return flag;
       
       return checkFull(index, left) && checkFull(index, right);
    }
    public static void preOrderTraversal(int[] index, int root){
        int left = leftChild(root), right = rightChild(root);
        
        //To check whether the root is not out of bounds and index[root] not equal to " - "
        if(root < index.length && index[root] != -1){
            System.out.print(index[root] + " ");  //called the current root/current node
            preOrderTraversal(index, left);  //called left child
            preOrderTraversal(index, right);  //called right child
        }
    }
    public static void inOrderTraversal(int[] index, int root){
        int left = leftChild(root), right = rightChild(root);
        
        //To check whether the root is not out of bounds and index[root] not equal to " - "
        if(root < index.length && index[root] != -1){
            inOrderTraversal(index, left);  //called left child
            System.out.print(index[root] + " ");  //called the current root/current node
            inOrderTraversal(index, right);  //called right child
        }
    }
    public static void postOrderTraversal(int[] index, int root){
        int left = leftChild(root), right = rightChild(root);
        
        //To check whether the root is not out of bounds and index[root] not equal to " - "
        if(root < index.length && index[root] != -1){
            postOrderTraversal(index, left);  //called left child
            postOrderTraversal(index, right);  //called right child
            System.out.print(index[root] + " ");  //called the current root/current node
        }
    }
}
