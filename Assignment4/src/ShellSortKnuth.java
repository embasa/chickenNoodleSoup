public  class  ShellSortKnuth extends MySort{
    @Override
    public <AnyType extends Comparable<? super AnyType>> AnyType[] sort(AnyType[] a) {
        int j;
        int k = 1;
        moves = 0;
        comparisons = 0;
        while( (int)(Math.pow(3,k+1)-1)/2 < a.length/2){
            k++;
        }

        for (int h = (int)(Math.pow(3,k)-1)/2 ; h > 0; h =(int)( Math.pow(3,--k)-1)/2 ) {
            for (int i = h; i < a.length; i++) {
                AnyType tmp = a[i];
                for (j = i; j >= h && (++comparisons > 0 ) && tmp.compareTo(a[j - h]) < 0; j-=h ) {
                    a[j] = a[j - h];
                    moves++;
                }
                if(a[j] != tmp){
                    a[j] = tmp;
                    moves++;
                }
            }
        }
        return a;
    }
}
