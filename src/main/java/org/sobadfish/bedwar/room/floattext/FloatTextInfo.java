package org.sobadfish.bedwar.room.floattext;

import org.sobadfish.bedwar.BedWarMain;
import org.sobadfish.bedwar.entity.BedWarFloatText;
import org.sobadfish.bedwar.item.ItemInfo;
import org.sobadfish.bedwar.item.config.MoneyItemInfoConfig;
import org.sobadfish.bedwar.player.PlayerInfo;
import org.sobadfish.bedwar.room.GameRoom;

import java.io.IOException;

public class FloatTextInfo {

    public FloatTextInfoConfig floatTextInfoConfig;

    public BedWarFloatText bedWarFloatText;

    public FloatTextInfo(FloatTextInfoConfig config){
        this.floatTextInfoConfig = config;
    }

    public FloatTextInfo init(){
//        if(!floatTextInfoConfig.position.getChunk().isLoaded()){
//            floatTextInfoConfig.position.getLevel().loadChunk(floatTextInfoConfig.position.getChunkX(),floatTextInfoConfig.position.getChunkZ());
//
//
//        }
        try{
            bedWarFloatText = BedWarFloatText.showFloatText(floatTextInfoConfig.name,floatTextInfoConfig.position,"");
        }catch (Exception e){
            return null;
        }

        return this;
    }

    public boolean stringUpdate(GameRoom room){
        String text = floatTextInfoConfig.text;
        if(room == null){
            return false;
        }
        BedWarMain.sendMessageToConsole("测试浮空字正在更新.."+floatTextInfoConfig.name);
        for(ItemInfo moneyItemInfoConfig: room.getWorldInfo().getInfos()){
            MoneyItemInfoConfig config = moneyItemInfoConfig.getItemInfoConfig().getMoneyItemInfoConfig();
            text = text
                    .replace("%"+config.getName()+"%",config.getCustomName())
                    .replace("%"+config.getName()+"-time%", PlayerInfo.formatTime1((moneyItemInfoConfig.getResetTick() - moneyItemInfoConfig.getTick()))+"");
        }
        if(bedWarFloatText != null){
            bedWarFloatText.setText(text);
        }
        return true;
    }
}
