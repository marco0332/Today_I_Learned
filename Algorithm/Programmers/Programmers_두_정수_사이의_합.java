class Solution {
  public long solution(int a, int b) {
      long minVal = Math.min(a,b);
      long maxVal = Math.max(a,b);

      return (maxVal - minVal + 1) * (minVal + maxVal) / 2;
  }
}