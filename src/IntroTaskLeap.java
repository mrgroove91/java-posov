class IntroTaskLeap {
    public static void main(String[] args){
        int x = 1004; //x - данный год
        if (x%4==0){
            if (!(x%100==0 && x%400!=0)){
                System.out.println("год високосный");
            }
            else{
                System.out.println("год не високосный");
            }
        }
        else{
            System.out.println("год не високосный");
        }
    }
}
