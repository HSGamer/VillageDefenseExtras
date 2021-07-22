package me.hsgamer.villagedefenseextras.kit;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaUtils;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.events.api.CBPlayerInteractEvent;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.xseries.XMaterial;
import plugily.projects.villagedefense.commonsbox.minecraft.helper.ArmorHelper;
import plugily.projects.villagedefense.commonsbox.minecraft.helper.WeaponHelper;
import plugily.projects.villagedefense.commonsbox.minecraft.item.ItemBuilder;
import plugily.projects.villagedefense.commonsbox.minecraft.item.ItemUtils;
import plugily.projects.villagedefense.handlers.PermissionsManager;
import plugily.projects.villagedefense.handlers.language.Messages;
import plugily.projects.villagedefense.kits.basekits.PremiumKit;
import plugily.projects.villagedefense.user.User;

import java.util.List;
import java.util.Optional;

import static me.hsgamer.villagedefenseextras.Utils.*;
import static plugily.projects.villagedefense.utils.Utils.splitString;

public class AngelKit extends PremiumKit implements Listener {
    public AngelKit() {
        setName(MessageConfig.KIT_ANGEL_NAME.getValue());
        List<String> description = splitString(MessageConfig.KIT_ANGEL_DESCRIPTION.getValue(), 40);
        setDescription(description.toArray(new String[0]));
        VillageDefenseExtras.getInstance().registerListener(this);
    }

    @Override
    public boolean isUnlockedByPlayer(Player player) {
        return PermissionsManager.isPremium(player) || player.hasPermission("vdextra.kit.angel");
    }

    @Override
    public void giveKitItems(Player player) {
        ArmorHelper.setColouredArmor(Color.GRAY, player);
        player.getInventory().addItem(WeaponHelper.getUnBreakingSword(WeaponHelper.ResourceType.WOOD, 10));
        player.getInventory().addItem(new ItemBuilder(XMaterial.FEATHER.parseMaterial())
                .name(MessageConfig.KIT_ANGEL_ITEM_NAME.getValue())
                .lore(splitString(MessageConfig.KIT_ANGEL_ITEM_LORE.getValue(), 40))
                .build());
    }

    @Override
    public Material getMaterial() {
        return Material.FEATHER;
    }

    @Override
    public void reStock(Player player) {
        // EMPTY
    }

    @EventHandler
    public void onInteract(CBPlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = VersionUtils.getItemInHand(player);
        if (!ItemUtils.isItemStackNamed(itemStack) || !checkDisplayName(itemStack, MessageConfig.KIT_ANGEL_ITEM_NAME.getValue())) {
            return;
        }

        Optional<Arena> optionalArena = getArena(player);
        if (!optionalArena.isPresent()) {
            return;
        }
        Arena arena = optionalArena.get();

        User user = getUser(player);
        if (user.isSpectator()) {
            player.sendMessage(getPlugin().getChatManager().colorMessage(Messages.SPECTATOR_WARNING));
            return;
        }

        if (!(user.getKit() instanceof AngelKit)) {
            arena.getPlayersLeft().forEach(player1 -> {
                Location location = player1.getLocation();
                location.getWorld().strikeLightningEffect(location);
                player1.setHealth(0);
            });
            return;
        }

        if (user.getCooldown("angel") > 0) {
            String message = getPlugin().getChatManager().colorMessage(Messages.KITS_ABILITY_STILL_ON_COOLDOWN);
            message = message.replaceFirst("%COOLDOWN%", Long.toString(user.getCooldown("angel")));
            player.sendMessage(message);
            return;
        }

        Location startLocation = arena.getStartLocation();
        startLocation.getWorld().strikeLightningEffect(startLocation);
        ArenaUtils.bringDeathPlayersBack(arena);
        player.sendMessage(MessageConfig.KIT_ANGEL_REBORN_MESSAGE.getValue());
        user.setCooldown("angel", MainConfig.KIT_ANGEL_COOLDOWN.getValue());
    }
}
