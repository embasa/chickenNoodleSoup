/**
 * Created by bruno on 7/22/15.
 */
public class ListMergeSort extends  MySort{
    private int size = 0;
    protected class LinkNode<AnyType extends Comparable<? super AnyType>> {
        LinkNode<AnyType> next;
        LinkNode<AnyType> prev;
        AnyType data;

        protected LinkNode() {
            this(null);
        }

        protected LinkNode(AnyType data) {
            this.next = null;
            this.prev = null;
            this.data = data;
        }
    }

    protected class LinkedList< AnyType extends Comparable< ? super AnyType >> {
        LinkNode<AnyType> front;
        LinkNode<AnyType> end;


        /**        LinkNode temp = new LinkNode();
         * front and end are sentinel nodes as to implify insertion
         */
        public LinkedList(){
            front = null;// new LinkNode<>();
            end = null;//new LinkNode<>();
        }
    }

    public < AnyType extends Comparable< ? super AnyType > > void sort( AnyType[] a ) {
        if(a.length == 0){
            return;
        }
        comparisons = 0;
        moves = 0;
        time = System.currentTimeMillis();
        LinkNode<AnyType> temp = new LinkNode<>(a[0]);

        LinkNode<AnyType> front = temp;//= new LinkNode<>(a[0]);
        size++;
        for(int i = 1;i < a.length; i ++){
            temp.next = new LinkNode<>(a[i]);
            temp.next.prev = temp;
            temp = temp.next;
            size++;
        }
        if(size != a.length){
            System.out.print("Error in size\n ");
        }
        front = mergeSort(front);
        for (int i = 0;i< a.length;i++){
            a[i] = front.data;
            front = front.next;
        }
        time = System.currentTimeMillis()-time;
    }
    public < AnyType extends Comparable< ? super AnyType > > LinkNode<AnyType> mergeSort(LinkNode<AnyType> node) {
        if(node == null|| node.next == null){
            return node;
        }
        LinkNode<AnyType> head1 = node;//there is at least two node
        LinkNode<AnyType> head0 = node.next;// ^^
        node = node.next.next;//this is either null or the next node
        head1.next = null;
        head0.next = null;
        head0.prev = null;
        LinkNode<AnyType> list1 = head1;
        LinkNode<AnyType> list0 = head0;
        //this is either null or the next node
        int count = 1;
        while( node != null ){
            switch (count%2){
                case 1:
                    list1.next = node;//connect node to end of list
                    node.prev = list1;// connect node to list too
                    list1 = node;// move list reference to new end
                    node = node.next;//move node forward
                    list1.next = null;
                    break;
                default:
                    list0.next = node;//connect node to end of list
                    node.prev = list0;// connect node to list too
                    list0 = node;// move list reference to new end
                    node = node.next;//move node forward
                    list0.next = null;
                    break;
            }
            count++;
        }
        head1 = mergeSort(head1);
        head0 = mergeSort(head0);
        return merge(head1,head0);
    }

    public < AnyType extends Comparable< ? super AnyType > >void print(LinkNode<AnyType> node){
        if(node == null)
            return;
        LinkNode<AnyType> temp = node;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public < AnyType extends Comparable< ? super AnyType > > LinkNode<AnyType> merge(LinkNode<AnyType> a, LinkNode<AnyType> b){
        LinkNode<AnyType> head;//a.data.compareTo(b.data)<0 ? a : b;
        LinkNode<AnyType> temp;
        //if(a.data.compareTo(b.data)<0){
        if(compare(a.data,b.data)<0){
            head = a;
            a = a.next;// hold on to whatever a.next is pointing to
        }else{
            head = b;
            b = b.next;
        }
        temp = head;//temp is NOT NULL!!!!! foo shoo
        while( a != null && b != null ){
//            if(a.data.compareTo(b.data)<0){
            if(compare(a.data,b.data)<0){
                temp.next = a;
                a.prev = temp;
                a = a.next;
                moves++;
            }else {
                temp.next = b;
                b.prev = temp;
                b = b.next;
                moves++;
            }
            temp = temp.next;
        }
        if(a != null){
            temp.next = a;
            a.prev = temp;
            moves++;
        }
        if(b != null){
            temp.next = b;
            b.prev = temp;
            moves++;
        }
        return head;
    }

}
