class IntroTaskCat {
    public static void main(String[] args) {
        int x = 22; //x - данное число
        String y=" котов";
        if (x%10==1 && (x%100)!=11) {
            y=" кот";
        }else if((x%10==2 && (x%100)!=12) || (x%10==3 && (x%100)!=13) || (x%10==4 && (x%100)!=14)){
            y=" кота";
        }
        System.out.println(Integer.toString(x)+y);
    }
}
