package treetest;

public class Solution124 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}

        TreeNode(int val){
            this.val=val;
        }
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }

    class Solution{
        int res=Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root){
            if(root==null){
                return 0;
            }
            oneSideMax(root);
            return res;
        }

        int oneSideMax(TreeNode root){
            if(root==null){
                return 0;
            }

            int leftMaxSum =Math.max(0,oneSideMax(root.left));
            int rightMaxSum =Math.max(0,oneSideMax(root.right));

            int pathMaxSum =root.val +leftMaxSum +rightMaxSum;
            res=Math.max(res,pathMaxSum);

            return Math.max(leftMaxSum,rightMaxSum) +root.val;
        }
    }
}
