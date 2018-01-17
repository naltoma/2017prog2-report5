package jp.ac.uryukyu.ie.tnal;

/**
 * Created by tnal on 2016/11/13.
 */
public class Main {
    public static void main(String[] args){
        Hero hero = new Hero("勇者", 10, 5, "勇者死んじゃった", 0.4, "会心の一撃");
        Enemy enemy = new Enemy("スライム", 6, 30, "モンスター死亡", 0.3, "痛恨の一撃");
        //LivingThing enemy = new LivingThing();

        System.out.printf("%s vs. %s\n", hero.getName(), enemy.getName());

        int turn = 0;
        while( hero.isDead() == false && enemy.isDead() == false ){
            turn++;
            System.out.printf("%dターン目開始！\n", turn);
            hero.attack(enemy);
            enemy.attack(hero);
        }
        System.out.println("戦闘終了");
    }
}