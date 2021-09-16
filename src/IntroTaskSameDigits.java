class IntroTaskSameDigits {
    public static void main(String[] args) {
        int x = 1010; //x - данное число
        if ((x%10==(x/10)%10)&&(x!=0)){
            System.out.println("на конце две одинаковые цифры");
        } else {
            System.out.println("на конце что-то другое");
        }
    }
}
