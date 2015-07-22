package com.bapcraft.bclotto.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bapcraft.bclotto.BCLotto;
import com.bapcraft.bclotto.Drawing;
import com.bapcraft.bclotto.Ticket;

public class CommandAddTicket implements CommandExecutor {

	/*
	 * `addbcticket <uuid>`
	 * Example UUID: 0e51946b-9bd3-4ded-a738-4217ec94b1d7
	 *     (^-- I generated when when checking the format.)
	 */
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			sender.sendMessage("This can only be run on the console!");
			return false;
			
		} else {
			
			Ticket added = null;
			
			// Let's make sure that the UUID makes sense.
			try {
				added = new Ticket(UUID.fromString(args[0]));
			} catch (IllegalArgumentException iae) {
				
				// The decoding probably failed.  Notify the user.
				sender.sendMessage("UUID decoding failed!  Probaby wrong format!");
				sender.sendMessage("UUID needs to be in format `XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX`."); // Could I use a \n for this line?
				return false;
				
			}
			
			// Now that we have the UUID in a format we can work with, we can move on...
			
			Drawing draw = BCLotto.instance.activeDrawing;
			
			try {
				draw.addTicket(added);
			} catch (IllegalStateException ise) {
				sender.sendMessage("New lottery hasn't started yet, please wait a second...");
			}
			
			if (draw.isWinnerNormallyAvailable()) {
				
				UUID winner = draw.getWinner();
				
				// TODO Weighted chance probability.
				
			}
			
		}
		
		return true;
		
	}

}