import java.util.*;

public class dsuQuestion {

    static int[] size, par;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 && grid[0].length == 0)
            return 0;

        int n = grid.length, m = grid[0].length;
        par = new int[n * m];
        size = new int[n * m];

        int maxSize = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                par[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }

        int[][] dir = { { 1, 0 }, { 0, 1 } };
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {

                    int p1 = findPar(i * m + j);
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            int p2 = findPar(r * m + c);
                            if (p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }

                    maxSize = Math.max(maxSize, size[p1]);
                }
            }
        return maxSize;
    }

    public String smallestEquivalentString(String s1, String s2, String baseString) {
        par = new int[26];

        for (int i = 0; i < 26; i++)
            par[i] = i;

        for (int i = 0; i < s1.length(); i++) {
            int p1 = findPar(s1.charAt(i) - 'a');
            int p2 = findPar(s2.charAt(i) - 'a');

            par[p1] = Math.min(p1, p2);
            par[p2] = Math.min(p1, p2);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseString.length(); i++) {
            char ch = (char) (findPar(baseString.charAt(i) - 'a') + 'a');
            sb.append(ch);
        }

        return sb.toString();
    }

    public boolean equationsPossible(String[] equations) {
        par = new int[26];

        for (int i = 0; i < 26; i++)
            par[i] = i;

        for (String s : equations) {
            char ch1 = s.charAt(0), ch2 = s.charAt(1), ch3 = s.charAt(3);
            int p1 = findPar(ch1 - 'a');
            int p2 = findPar(ch3 - 'a');

            if (ch2 == '=' && p1 != p2) {
                par[p1] = p2;
            }
        }

        for (String s : equations) {
            char ch1 = s.charAt(0), ch2 = s.charAt(1), ch3 = s.charAt(3);
            int p1 = findPar(ch1 - 'a');
            int p2 = findPar(ch3 - 'a');

            if (ch2 == '!' && p1 == p2) {
                return false;
            }
        }

        return true;
    }

    public boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }

        return true;
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length, group = n;
        par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);

                    if (p1 != p2) {
                        par[p1] = p2;
                        group--;
                    }
                }
            }
        return group;
    }

    public List<Integer> numIslands2(int n, int m, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        int dir[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        par = new int[n * m];
        Arrays.fill(par, -1);

        int count = 0;
        for (int[] p : positions) {
            int i = p[0], j = p[1];
            if (par[i * m + j] == -1) {
                count++;
                par[i * m + j] = i * m + j;

                int p1 = findPar(i * m + j);
                for (int d = 0; d < 4; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && par[r * m + j] != -1) {
                        int p2 = findPar(r * m + c);
                        if (p1 != p2) {
                            par[p2] = p1;
                            count--;
                        }
                    }
                }
            }

            ans.add(count);
        }

        return ans;
    }

    // 684
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        par = new int[n + 1];
        for (int i = 0; i <= n; i++)
            par[i] = i;

        int[] ans = null;
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                par[p2] = p1;
            } else {
                ans = e;
            }
        }

        return ans;
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> allPipes = new ArrayList<>();
        for (int[] a : pipes)
            allPipes.add(a);

        for (int i = 0; i < wells.length; i++)
            allPipes.add(new int[] { 0, i + 1, wells[i] });

        Collections.sort(allPipes, (a, b) -> {
            return a[2] - b[2];
        });

        par = new int[n + 1];
        int ans = 0;

        for (int i = 0; i < n; i++)
            par[i] = i;

        for (int[] a : allPipes) {
            int u = a[0], v = a[1], w = a[2];

            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                par[p2] = p1;
                ans += w;
            }
        }

        return ans;

    }

    public static int mrPresident(int[][] edges, int N, int K) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++)
            par[i] = i;

        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Integer> road = new ArrayList<>();

        int component = N, minCost = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                par[p2] = p1;
                component--;
                minCost += w;
                road.add(w);
            }
        }

        if (component > 1)
            return -1;

        int superRoad = 0;
        for (int i = road.size() - 1; i >= 0; i--) {
            if (minCost <= K)
                break;
            minCost = minCost - road.get(i) + 1;
            superRoad++;
        }

        return minCost <= K ? superRoad : -1;
    }

    // 959
    public static int regionsBySlashes(String[] grid) {
        if (grid.length == 0)
            return 0;
        int n = grid.length, m = n + 1, region = 1;
        par = new int[m * m];
        for (int i = 0; i < m * m; i++) {
            par[i] = i;

            int r = i / m, c = i % m;          //for corner 
            if (r == 0 || c == 0 || r == m - 1 || c == m - 1)
                par[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            String s = grid[i];
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);

                int p1 = 0, p2 = 0;
                if (ch == '/') {
                    p1 = findPar(i * m + j + 1);
                    p2 = findPar((i + 1) * m + j);
                } else if (ch == '\\') {
                    p1 = findPar(i * m + j);
                    p2 = findPar((i + 1) * m + j + 1);
                } else {                    // for space
                    continue;
                }

                if (p1 != p2) {
                    par[p2] = p1;
                } else
                    region++;
            }
        }

        return region;
    }
}
