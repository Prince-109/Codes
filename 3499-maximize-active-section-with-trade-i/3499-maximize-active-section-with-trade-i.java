class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";
        int m = t.length();

        ArrayList<Character> ch = new ArrayList<>();
        ArrayList<Integer> len = new ArrayList<>();

        int i = 0;
        while (i < m) {
            char c = t.charAt(i);
            int j = i;
            while (j < m && t.charAt(j) == c) j++;
            ch.add(c);
            len.add(j - i);
            i = j;
        }

        int bestGain = 0;

        for (int k = 1; k + 1 < ch.size(); k++) {
            if (ch.get(k) == '1'
                    && ch.get(k - 1) == '0'
                    && ch.get(k + 1) == '0') {

                int gain = len.get(k - 1) + len.get(k + 1);
                bestGain = Math.max(bestGain, gain);
            }
        }

        return ones + bestGain;
    }
}