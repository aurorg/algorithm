package datastruct.recursion;

public class MiGongDemo {
    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        //地图
        int [][] map=new int[8][7];

        //使用1表示墙
        //把上下全部置为1
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }

        //左右全部置为1
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }

        //设置挡板
        map[3][1]=1;
        map[3][2]=1;

        //输出地图
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯找路
        setWay(map,1,1);
        System.out.println("小球走过的路");

        //输出新的地图
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    //使用递归回溯来给小球找路
    //i,j表示从哪个位置触发开始找
    //小球能到【6】【5】说明通路找到
    //map[i][j] 为0表示该点没有走过
    //          为1表示墙
    //          为2表示通路可以走
    //            3 该点已经走过，但是走不通
    //走迷宫的时候，需要确定一个策略(方法) 下->右->上->左  ，如果该点走不通，再回溯
    /**
     *
     * @param map 地图
     * @param i  位置的行
     * @param j  位置的列
     * @return   如果找到通路，返回true，否则false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){  //通路已经找到
            return true;
        }else{
            if(map[i][j]==0){ //当前这个点还没有走过
                //按照策略走
                map[i][j]=2; //假定该点可以走通
                if(setWay(map,i+1,j)){ //向下走
                    return true;
                }else if(setWay(map,i,j+1)){ //向右走
                    return true;
                }else if (setWay(map,i-1,j)) { //向上走
                    return true;
                } else if (setWay(map,i,j-1)) { //向左走
                    return true;
                } else{
                    //说明该点走不通,置为3
                    map[i][j]=3;
                    return false;
                }
            }else{  //如果map[i][j] != 0;可能是1，2，3
                return false;
            }
        }

    }

}
