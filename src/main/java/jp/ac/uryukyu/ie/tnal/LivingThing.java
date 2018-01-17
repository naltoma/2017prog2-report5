package jp.ac.uryukyu.ie.tnal;

/**
 * ヒーロークラス。
 *  String name; //敵の名前
 *  int hitPoint; //敵のHP
 *  int attack; //敵の攻撃力
 *  boolean dead; //敵の生死状態。true=死亡。
 * Created by tnal on 2016/11/13.
 */
public class LivingThing {
    private String name;
    private int hitPoint;
    private int attack;
    private boolean dead;
    private String deadMessage;
    private double criticalRate;
    private String criticalMessage;

    /**
     * コンストラクタ。名前、最大HP、攻撃力を指定する。
     * @param name ヒーロー名
     * @param maximumHP ヒーローのHP
     * @param attack ヒーローの攻撃力
     */
    public LivingThing (String name, int maximumHP, int attack, String deadMessage, double criticalRate, String criticalMessage) {
        this.name = name;
        hitPoint = maximumHP;
        this.attack = attack;
        dead = false;
        System.out.printf("%sのHPは%d。攻撃力は%dです。\n", name, maximumHP, attack);
        this.deadMessage = deadMessage;
        this.criticalRate = criticalRate;
        this.criticalMessage = criticalMessage;
    }

    /**
     * getterメソッドと同等。生死をboolean表現しているためメソッド名をisDead()とした。
     * @return boolean
     */
    public boolean isDead(){
        return  dead;
    }

    public String getName(){
        return name;
    }

    public int getHitPoint(){ return hitPoint; }
    public void updateHitPoint(int damage){ hitPoint -= damage; }
    public boolean getDead(){ return dead; }
    public void setDead(boolean flag){ dead = flag; }
    public int getAttack(){ return attack; }

    /**
     * Enemyへ攻撃するメソッド。
     * attackに応じて乱数でダメージを算出し、hero.wounded()によりダメージ処理を実行。
     * @param opponent 攻撃対象
     */
    public void attack(LivingThing opponent){
        if( isDead() == false ) {
            int damage = (int) (Math.random() * getAttack());
            if ( damage == 0 ) {
                System.out.printf("%sの攻撃！,,,だが、%sは攻撃を回避した！\n", getName(), opponent.getName());
            }else {
                if( Math.random() < criticalRate ){
                    damage *= 2;
                    System.out.printf("%sの攻撃！%s！！%sに%dのダメージを与えた！！\n", getName(), criticalMessage, opponent.getName(), damage);
                }else {
                    System.out.printf("%sの攻撃！%sに%dのダメージを与えた！！\n", getName(), opponent.getName(), damage);
                }
                opponent.wounded(damage);
            }
        }
    }

    /**
     * 自身へ攻撃されたときのダメージ処理をするメソッド。
     * 指定されたダメージを hitPoint から引き、死亡判定を行う。
     * @param damage 受けたダメージ
     */
    public void wounded(int damage){
        updateHitPoint(damage);
        if( getHitPoint() <= 0 ) { //0の時も死ぬように修正
            setDead(true);
            System.out.printf("%s\n", deadMessage);
        }
    }

}