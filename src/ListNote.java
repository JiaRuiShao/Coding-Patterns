class ListNode {
    public int value; // could be any type
    public ListNode next;
    // public ListNode prev;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(4);
    }
}
