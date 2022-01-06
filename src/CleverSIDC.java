import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CleverSIDC {

    int threshold;
    long keys[];
    int pointer;
    HashTable hashTable;

    public CleverSIDC(){
        setSIDCThreshold(5000);
        if(getThreshold()>10000)
            hashTable = new HashTable((int)(getThreshold()/2));
        else
            hashTable = new HashTable((int)(getThreshold()));
        keys = new long[getThreshold()];
        pointer =-1;

    }
    public CleverSIDC(int size){
        setSIDCThreshold(size);
        if(getThreshold()>10000)
            hashTable = new HashTable((int)(getThreshold()/2));
        else
            hashTable = new HashTable((int)(getThreshold()));
        keys = new long[getThreshold()];
        pointer =-1;

    }
    public void setSIDCThreshold (int size){

        /**
        * where 100 ≤ Size ≤ ~500,000 is an integer number that defines the size of the list.
        * This size is very important as it will determine what data types or data structures
        * will be used (i.e. a Tree, Hash Table, AVL tree, binary tree, sequence, etc.);
        * */

        this.threshold = size;

    }
    public int getThreshold() {
        return threshold;
    }
    public long generate(){

        /**
         * randomly generates new non-existing key of 8 digits;
         */
        Scanner sc=null;
        long key = 0;
        try {
            sc = new Scanner(new FileInputStream("NASTA_test_file1.txt"));
            key = sc.nextLong();
            while(hashTable.exist(key)){
                key = sc.nextLong();
            }
            return key;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }

    public long [] allKeys(){
        /**
         * return all keys in CleverSIDC as a sorted sequence
         */
        long []sorted_keys = null;
        if(getThreshold()<10000){
            System.out.println("------------Starting Merge Sort-----------------");
            MergeSort ms = new MergeSort();
            sorted_keys = ms.mergeSort(keys,0, pointer);
        }else{
            System.out.println("------------Starting Quick Sort-----------------");
            QuickSort qs = new QuickSort();
            sorted_keys = qs.quickSort(keys,0,pointer);
        }
        System.out.println("------------Sorting Ended-----------------");
        return sorted_keys;
    }
    public void add(Long key, Student student){
        /**
         * add an entry for the given key and value
         */
        //bst.insert(key);
        hashTable.insert(key,student);
        if(pointer<keys.length) {
            pointer++;
            keys[pointer] = key;
        }
    }
    public void remove(long key){
        /**
         * remove the entry for the given key
         */
        hashTable.remove(key);
        for (int i=0; i<=pointer; i++){
            if(keys[i]==key){
                keys[i]=0;
                int j=i+1;
                for(; j<=pointer&&j<keys.length;j++){
                    keys[j-1]=keys[j];
                }
                keys[j-1] = 0;
                break;
            }
        }
        pointer--;

    }
    public String getValues(long key){
        /**
         * return the values of the given key
         */
        return hashTable.getValue(key);
    }
    public long nextKey(long key){
        /**
         * return the key for the successor of key
         * returns -1 if next key doesn't exist
         */
        if(key == keys[pointer])
            return -1;
        for(int i=0; i<pointer; i++){
            if(keys[i]==key) return keys[i+1];
        }

        return -1;
    }
    public long prevKey(long key){
        /**
         * return the key for the predecessor of key
         * returns -1 if previous key doesn't exist
         */
        if(key == keys[0])
            return -1;
        for(int i=1; i<pointer; i++){
            if(keys[i]==key) return keys[i-1];

        }
        return -1;
    }
    public long[] rangeKey(long key1, long key2){
        /**
         * returns the number of keys that are within the specified range of the two keys key1 and key2.
         */
        long []range_keys = new long[getThreshold()];
        int c=0;
        for(int i=0; i<threshold; i++){
            if(keys[i]>=key1 && keys[i]<=key2)
                range_keys[c++]=keys[i];
        }
        long [] new_range = new long[c];
        for(int i=0; i<c; i++){
            new_range[i]=range_keys[i];
        }

        return new_range;
    }
    public  void printHT(){
        hashTable.print();
    }

    public static void main (String []args){
        String [] firstNames = {"John", "Dina","Layla","Samantha","Rock","Bill","Charles","Oprah","Julia","Justin","Rob"};
        String [] lastNames = {"Dickens", "Brown", "Goldman","Beiber","Brown","Myers","Stark","Winfre","Wilde","Gates","Wick","Dyne","Rick"};
        String [] dob = {"1-1-99","1-4-87", "11-10-98","13-12-96","30-10-97","2-8-88","4-4-93","5-6-80","7-8-90","23-06-91","5-4-88","25-11-89"};

        int N = 5;
        CleverSIDC cleverSIDC = new CleverSIDC(N);

        for(int i=0; i<cleverSIDC.getThreshold(); i++){
            //1. generate
            System.out.println("1. Calling generate():");
            Long sidc = cleverSIDC.generate();
            System.out.println("Key generated: "+sidc);
            System.out.println();
            Student student = new Student();
            student.setSIDC(sidc);
            student.setFirstName(firstNames[(int)(Math.random()*11)]);
            student.setFamilyName(lastNames[(int)(Math.random()*13)]);
            student.setDOB(dob[(int)(Math.random()*12)]);
            //2. add
            System.out.println("2. Calling add(key):");
            cleverSIDC.add(sidc, student);
            System.out.println("Adding key "+sidc+" with "+student.toString());
            System.out.println();
        }

        //3. setThreshold
        System.out.println("3. Calling setSIDCThreshold(N=number of students):");
        cleverSIDC.setSIDCThreshold(N);
        System.out.println("Threshold set to: "+cleverSIDC.getThreshold());
        System.out.println();
        //4. getValues
        System.out.println("4. Calling getValues(key):");
        long key = 33255593, key2 = 837470690;
        System.out.println("Getting values of key- "+key+" : "+cleverSIDC.getValues(key));
        System.out.println();
        //5. nextKey
        System.out.println("5. Calling nextKey(key):");
        long k2 = cleverSIDC.nextKey(33255593);
        if(k2 != -1)
            System.out.println("Getting values of next key of key- "+key+" : "+cleverSIDC.getValues(k2));
        System.out.println();
        //6. prevKey
        System.out.println("6. Calling prevKey(key):");
        long k1 = cleverSIDC.prevKey(33255593);
        if(k1 != -1)
            System.out.println("Getting values of previous key of key- "+key+" : "+cleverSIDC.getValues(k1));
        System.out.println();
        //7. rangeKey
        System.out.println("7. Calling rangeKey(key1, key2):");
        long []range = cleverSIDC.rangeKey(key, key2);
        System.out.println("-------------Showing keys in the range "+key+" to "+key2+"-------------");
        for(int c=0; c<range.length;c++){
            System.out.print(range[c]+" ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println();
        long startTime = System.nanoTime();
        //9. remove
        System.out.println("9. Calling remove(key):");
        System.out.println("Removing key "+key);
        cleverSIDC.remove(key);
        System.out.println("Getting values of key "+key+" : "+cleverSIDC.getValues(33255593));
        System.out.println();
        //8. allKeys
        System.out.println("8. Calling allKeys(key):");
        long sorted[] = cleverSIDC.allKeys();
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
//        System.out.println(totalTime);
        System.out.println("--------------Sorted keys---------------");
        for(int c=0; c<sorted.length;c++){
            if(sorted[c]!=0)
            System.out.println(cleverSIDC.getValues(sorted[c])+" ");
        }
        System.out.println();


    }

}

//Output:

/*
1. Calling generate():
Key generated: 33240013

2. Calling add(key):
Adding key 33240013 with Student{SIDC=33240013, familyName='Dyne', firstName='Charles', DOB='23-06-91'}

1. Calling generate():
Key generated: 33255593

2. Calling add(key):
Adding key 33255593 with Student{SIDC=33255593, familyName='Wick', firstName='Rock', DOB='30-10-97'}

1. Calling generate():
Key generated: 3326261

2. Calling add(key):
Adding key 3326261 with Student{SIDC=3326261, familyName='Goldman', firstName='Julia', DOB='1-1-99'}

1. Calling generate():
Key generated: 83747069

2. Calling add(key):
Adding key 83747069 with Student{SIDC=83747069, familyName='Goldman', firstName='Rob', DOB='23-06-91'}

1. Calling generate():
Key generated: 33266743

2. Calling add(key):
Adding key 33266743 with Student{SIDC=33266743, familyName='Brown', firstName='Rob', DOB='25-11-89'}

3. Calling setSIDCThreshold(N=number of students):
Threshold set to: 5

4. Calling getValues(key):
Getting values of key- 33255593 : Student{SIDC=33255593, familyName='Wick', firstName='Rock', DOB='30-10-97'}

5. Calling nextKey(key):
Getting values of next key of key- 33255593 : Student{SIDC=3326261, familyName='Goldman', firstName='Julia', DOB='1-1-99'}

6. Calling prevKey(key):
Getting values of previous key of key- 33255593 : Student{SIDC=33240013, familyName='Dyne', firstName='Charles', DOB='23-06-91'}

7. Calling rangeKey(key1, key2):
-------------Showing keys in the range 33255593 to 837470690-------------
33255593 83747069 33266743
-----------------------------------------------------------------------

8. Calling allKeys(key):
------------Starting Merge Sort-----------------
------------Sorting Ended-----------------
--------------Sorted keys---------------
3326261 33240013 33255593 33266743 83747069
9. Calling remove(key):
Removing key 33255593
Getting values of key 33255593 : No student registered with SIDC 33255593


Process finished with exit code 0

 */
