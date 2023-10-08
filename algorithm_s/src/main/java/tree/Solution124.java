package tree;

public class Solution124 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

  class Solution{
        int res =Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root){
            if(root==null){
                return 0;
            }
            //计算单边路径和时顺便计算最大路径和
            oneSideMax(root);
            return res;
        }
        //计算从根节点root为起点的最大单边路径和
        int oneSideMax(TreeNode root){
            if(root==null){
                return 0;
            }
            int leftMaxSum = Math.max(0,oneSideMax(root.left));
            int rightMaxSum = Math.max(0,oneSideMax(root.right));

            int pathMaxSum =root.val +leftMaxSum +rightMaxSum;
            res=Math.max(res,pathMaxSum);  //比较最大单边和和总的和的大小，看哪个大

            return Math.max(leftMaxSum,rightMaxSum) +root.val;
        }
  }
}
