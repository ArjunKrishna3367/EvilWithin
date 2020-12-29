package automaton.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InfiniteLoop extends AbstractBronzeCard {

    public final static String ID = makeID("InfiniteLoop");

    //stupid intellij stuff attack, enemy, rare
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private boolean showCard;

    public InfiniteLoop(boolean showCard) {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = 2;
        thisEncodes();
        this.showCard = showCard;
        //if (showCard)
        //    cardsToPreview = new InfiniteLoop(false); //I had to.
    }

    public InfiniteLoop() {
        this(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public String getSpecialCompileText() {
        return "Add a copy of #yInfinite #yLoop to your hand with #b" + magicNumber + " more damage.";
    }

    @Override
    public void onCompile(AbstractCard function, boolean forGameplay) {
        if (forGameplay) {
            AbstractCard c = new InfiniteLoop();
            c.baseDamage += this.magicNumber;
            c.damage += this.magicNumber;
            if (upgraded) c.upgrade();
            makeInHand(c);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}