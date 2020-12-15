package automaton.actions;

import automaton.cards.AbstractBronzeCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FireFromPileAction extends AbstractGameAction {
    private CardGroup g;
    private int repetitions;

    public FireFromPileAction(CardGroup t, int amount, int repetitions) { //TODO: Can only choose playable cards. End immediately and quote if all cards can't be played.
        this.g = t;
        this.amount = amount;
        this.repetitions = repetitions;
    }

    public FireFromPileAction(CardGroup t, int amount) {
        this(t, amount, 0);
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            AbstractDungeon.gridSelectScreen.open(g, amount, false, "Choose."); //TODO: Localize
            tickDuration();
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (c instanceof AbstractBronzeCard) {
                        ((AbstractBronzeCard) c).inFire = true;
                    }
                    if (repetitions > 0) {
                        for(int i = 0; i < repetitions; i++) {
                            addToTop(new EasyAutoplayAction(c));
                        }
                    }
                    addToTop(new NewQueueCardAction(c, true));
                }
                isDone = true;
            }
        }
    }
}