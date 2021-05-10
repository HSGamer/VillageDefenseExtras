package me.hsgamer.villagedefenseextras.kit;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import plugily.projects.villagedefense.handlers.PermissionsManager;
import plugily.projects.villagedefense.handlers.language.Messages;
import plugily.projects.villagedefense.kits.basekits.PremiumKit;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.helper.ArmorHelper;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.helper.WeaponHelper;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.item.ItemBuilder;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.item.ItemUtils;
import plugily.projects.villagedefense.user.User;

import java.util.List;

import static me.hsgamer.villagedefenseextras.Utils.*;
import static plugily.projects.villagedefense.utils.Utils.splitString;

public class DefuserKit extends PremiumKit implements Listener {

    public DefuserKit() {
        setName(MessageUtils.colorize(MessageConfig.KIT_DEFUSER_NAME.getValue()));
        List<String> description = splitString(MessageUtils.colorize(MessageConfig.KIT_DEFUSER_DESCRIPTION.getValue()), 40);
        setDescription(description.toArray(new String[0]));
        VillageDefenseExtras.getInstance().registerListener(this);
    }

    @Override
    public boolean isUnlockedByPlayer(Player player) {
        return PermissionsManager.isPremium(player) || player.hasPermission("vdextra.kit.defuser");
    }

    @Override
    public void giveKitItems(Player player) {
        ArmorHelper.setColouredArmor(Color.RED, player);
        player.getInventory().addItem(WeaponHelper.getUnBreakingSword(WeaponHelper.ResourceType.WOOD, 10));
        player.getInventory().addItem(new ItemBuilder(XMaterial.SHEARS.parseMaterial())
                .name(MessageUtils.colorize(MessageConfig.KIT_DEFUSER_ITEM_NAME.getValue()))
                .lore(splitString(MessageUtils.colorize(MessageConfig.KIT_DEFUSER_ITEM_LORE.getValue()), 40))
                .build());
    }

    @Override
    public Material getMaterial() {
        return Material.TNT;
    }

    @Override
    public void reStock(Player player) {
        // EMPTY
    }

    @EventHandler
    public void onTntInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (!isInArena(player)) {
            return;
        }

        ItemStack itemStack = VersionUtils.getItemInHand(player);
        if (!ItemUtils.isItemStackNamed(itemStack) || !checkDisplayName(itemStack, MessageUtils.colorize(MessageConfig.KIT_DEFUSER_ITEM_NAME.getValue()))) {
            return;
        }

        Entity entity = event.getRightClicked();
        if (!(entity instanceof TNTPrimed)) {
            return;
        }
        TNTPrimed tntPrimed = (TNTPrimed) entity;

        User user = getUser(player);
        if (user.isSpectator()) {
            player.sendMessage(getPlugin().getChatManager().colorMessage(Messages.SPECTATOR_WARNING));
            return;
        }
        if (!(user.getKit() instanceof DefuserKit)) {
            tntPrimed.setFuseTicks(0);
            tntPrimed.setYield(tntPrimed.getYield() * MainConfig.KIT_DEFUSER_FAIL_YIELD_MULTIPLY.getValue());
            return;
        }

        if (user.getCooldown("defuser") > 0) {
            String message = getPlugin().getChatManager().colorMessage(Messages.KITS_ABILITY_STILL_ON_COOLDOWN);
            message = message.replaceFirst("%COOLDOWN%", Long.toString(user.getCooldown("clean")));
            player.sendMessage(message);
            return;
        }

        tntPrimed.remove();
        user.setCooldown("defuser", MainConfig.KIT_DEFUSER_COOLDOWN.getValue());
    }
}
