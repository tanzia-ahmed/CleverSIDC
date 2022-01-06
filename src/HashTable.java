public class HashTable {
    LinkedList [] ht;

    public HashTable(){
        ht = new LinkedList[0];
    }
    public HashTable(int size){
        ht = new LinkedList[size];
    }
    public void insert(long key, Student value){
        int index = hashfn1(key);
        if(ht[index]==null){
            ht[index] = new LinkedList(value,null);
        }else{
            ht[index].add(value);
        }

    }
    public void remove (long key){
        int index = hashfn1(key);
        if(ht[index]!=null){
            ht[index].remove(key);
        }else{

        }
    }
    private int hashfn1(long key){
        return (int) key % ht.length;
    }

    public String getValue(long key){
        int index = hashfn1(key);
        return ht[index].getValue(key);
    }
    public boolean exist(long key){
        int ind = hashfn1(key);
        if (ht[ind]==null)
            return false;
        else
            return ht[ind].exist(key);
    }

    public void print() {
        for(int i=0; i<ht.length; i++){
            System.out.println(ht[i]);
        }
    }
}
