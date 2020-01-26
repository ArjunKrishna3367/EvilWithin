package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import slimebound.SlimeboundMod;
import slimebound.cards.DecasProtection;
import slimebound.cards.DonusPower;
import slimebound.cards.PolyBeam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class RandomShapesCardAction extends AbstractGameAction {
    public boolean upgradeCard;

    public RandomShapesCardAction(boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        ArrayList<String> tmp = new ArrayList();
        Iterator var3 = CardLibrary.cards.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, AbstractCard> c = (Map.Entry) var3.next();
            if (c.getValue().hasTag(SlimeboundMod.STUDY_SHAPES)) {
                tmp.add(c.getKey());
            }
        }


        AbstractCard cStudy = CardLibrary.cards.get(tmp.get(AbstractDungeon.cardRng.random(0, tmp.size() - 1)));
        if (this.upgradeCard) {
            cStudy.upgrade();
        }

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cStudy));
        this.isDone = true;
    }

}



