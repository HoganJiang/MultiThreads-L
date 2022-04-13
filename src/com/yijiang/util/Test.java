package com.yijiang.util;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-04
 * @Description: com.yijiang.util
 */
public class Test {

    public static  void testNull(){
        Object o = null;
        if(null != null){
            System.out.println("not null");
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode head = new ListNode();
        int sum = 0;
        int carry = 0;
        ListNode cur = head;
        int cnt = 0;
        for(ListNode c1 = l1, c2 = l2; c1 != null || c2 != null;){
            if(c1 == null){
                sum = c2.val + carry;
                c2 = c2.next;
            } else if (c2 == null){
                sum = c1.val + carry;
                c1 = c1.next;
            } else {
                sum = c1.val + c2.val + carry;
                c1 = c1.next;
                c2 = c2.next;
            }

            if(sum >= 10){
                cur.val = sum % 10;
                carry = sum / 10;
            } else {
                cur.val = sum;
                carry = 0;
            }


            cur.next = new ListNode();
            cur = cur.next;
        }

        if(carry != 0){
            cur.next = new ListNode();
            cur = cur.next;
            cur.val = carry;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);

        ListNode headListNode = addTwoNumbers(l1, l2);

        for(ListNode cur = headListNode; cur != null; cur = cur.next){
            System.out.print(cur.val);
        }
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}