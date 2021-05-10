package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.Utils;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import plugily.projects.villagedefense.handlers.language.Messages;
import plugily.projects.villagedefense.kits.basekits.Kit;
import plugily.projects.villagedefense.kits.premium.*;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.events.api.CBPlayerInteractEvent;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.item.ItemUtils;
import plugily.projects.villagedefense.user.User;

import static me.hsgamer.villagedefenseextras.Utils.checkDisplayName;

public class RequiredKitItemFix implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(CBPlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isInArena(player)) {
            return;
        }
        User user = Utils.getUser(player);
        ItemStack eventItem = event.getItem();
        Kit kit = user.getKit();
        if (ItemUtils.isItemStackNamed(eventItem)) {
            if (checkDisplayName(eventItem, Messages.KITS_CLEANER_GAME_ITEM_NAME) && !(kit instanceof CleanerKit)) {
                sendMessage(player);
                event.setCancelled(true);
                return;
            }
        }
        ItemStack handItem = VersionUtils.getItemInHand(player);
        if (ItemUtils.isItemStackNamed(handItem)) {
            if (checkDisplayName(handItem, Messages.KITS_BLOCKER_GAME_ITEM_NAME) && !(kit instanceof BlockerKit)) {
                sendMessage(player);
                event.setCancelled(true);
            } else if (checkDisplayName(handItem, Messages.KITS_TELEPORTER_GAME_ITEM_NAME) && !(kit instanceof TeleporterKit)) {
                sendMessage(player);
                event.setCancelled(true);
            } else if (checkDisplayName(handItem, Messages.KITS_WIZARD_ESSENCE_ITEM_NAME) && !(kit instanceof WizardKit)) {
                sendMessage(player);
                event.setCancelled(true);
            } else if (checkDisplayName(handItem, Messages.KITS_WIZARD_STAFF_ITEM_NAME) && !(kit instanceof WizardKit)) {
                sendMessage(player);
                event.setCancelled(true);
            } else if (checkDisplayName(handItem, Messages.KITS_TORNADO_GAME_ITEM_NAME) && !(kit instanceof TornadoKit)) {
                sendMessage(player);
                event.setCancelled(true);
            }
        }
    }

    private void sendMessage(Player player) {
        MessageUtils.sendMessage(player, MessageConfig.FIX_REQUIRED_KIT_ITEM_CANNOT_USE_ITEM.getValue());
    }
}
