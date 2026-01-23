import java.util.*;

public class Solution {
    static class Node {
        long val;
        int id;
        int rank;
        Node prev, next;
        boolean alive = true;
        Node(long v, int i, int r) { val = v; id = i; rank = r; }
    }
    static class Pair {
        long sum;
        int rank;
        int leftId, rightId;
        Pair(long s, int r, int l, int rt) { sum = s; rank = r; leftId = l; rightId = rt; }
    }
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        Node[] byId = new Node[2 * n + 5];
        Node head = null, prev = null;
        for (int i = 0; i < n; i++) {
            Node cur = new Node(nums[i], i, i);
            byId[cur.id] = cur;
            if (head == null) head = cur;
            if (prev != null) { prev.next = cur; cur.prev = prev; }
            prev = cur;
        }
        int bad = 0;
        for (Node a = head; a != null && a.next != null; a = a.next) if (a.val > a.next.val) bad++;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
            if (a.sum != b.sum) return Long.compare(a.sum, b.sum);
            return Integer.compare(a.rank, b.rank);
        });
        for (Node a = head; a != null && a.next != null; a = a.next) pq.offer(new Pair(a.val + a.next.val, a.rank, a.id, a.next.id));
        int ops = 0, nextId = n;
        while (bad > 0) {
            Pair p = pq.poll();
            if (p == null) break;
            Node L = byId[p.leftId], R = byId[p.rightId];
            if (L == null || R == null || !L.alive || !R.alive || L.next != R) continue;
            Node P = L.prev, Nn = R.next;
            if (L.val > R.val) bad--;
            if (P != null && P.val > L.val) bad--;
            if (R.val > (Nn != null ? Nn.val : Long.MIN_VALUE)) if (Nn != null) bad--;
            Node M = new Node(L.val + R.val, nextId++, L.rank);
            byId[M.id] = M;
            M.prev = P; M.next = Nn; M.alive = true;
            if (P != null) P.next = M; else head = M;
            if (Nn != null) Nn.prev = M;
            L.alive = false; R.alive = false;
            ops++;
            if (P != null && P.val > M.val) bad++;
            if (Nn != null && M.val > Nn.val) bad++;
            if (P != null) pq.offer(new Pair(P.val + M.val, P.rank, P.id, M.id));
            if (Nn != null) pq.offer(new Pair(M.val + Nn.val, M.rank, M.id, Nn.id));
        }
        return ops;
    }
}