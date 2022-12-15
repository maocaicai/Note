/**
 * 69. x 的平方根
 * https://leetcode.cn/problems/sqrtx
 */
public class lee69 {
    /**
     * 精确到3位小数
     */
    public static double mySqrt(double x) {
        if (x == 0 || x == 1) return x;
        double l = 1, r = x;
        while (l <= r) {
            double m = ((r - l) / 2) + l;
            if (m * m < x) {
                //精确几位小数这就改成几
                l = m + 1e-3;
            }else if (m * m > x) {
                r = m - 1e-3;
            }else return m;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
}
