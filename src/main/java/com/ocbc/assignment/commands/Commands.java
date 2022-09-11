package com.ocbc.assignment.commands;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import com.ocbc.assignment.constants.AppConstants;

@Component
@ShellComponent
public class Commands {

	Map<String, Integer> cacheMap = new HashMap<>();

	private static String userName = null;
	private static String prevUserName = null;

	@Autowired
	public MessageSource messageSource;

	@ShellMethod("Login method for user")
	public String login(String userName) {

		if (!cacheMap.containsKey(userName)) {
			if (Commands.userName != null) {
				Commands.prevUserName = Commands.userName;
			}
			Commands.userName = userName;
			cacheMap.put(userName, 0);
			return messageSource.getMessage(AppConstants.LOGIN_POSITIVE_MESSAGE,
					new Object[] { userName, cacheMap.get(userName) }, Locale.ENGLISH);
		} else {
			if (null != Commands.userName && Commands.userName != userName) {
				Commands.prevUserName = Commands.userName;
				Commands.userName = userName;
			}
			if (cacheMap.get(userName) > 0) {
				return messageSource.getMessage(
						AppConstants.LOGIN_NEGATIVE_MESSAGE, new Object[] { userName,
								Math.abs(cacheMap.get(prevUserName)), prevUserName, cacheMap.get(userName) },
						Locale.ENGLISH);
			} else {
				return messageSource.getMessage(AppConstants.LOGIN_NEGATIVE_BALANCE_MESSAGE,
						new Object[] { userName, 0, Math.abs(cacheMap.get(userName)), prevUserName }, Locale.ENGLISH);

			}

		}
	}

	@ShellMethod("Topup Method")
	public String topup(Integer amount) {
		if (cacheMap.containsKey(userName)) {
			if (cacheMap.get(userName) < 0) {
				Integer tempValue = cacheMap.get(userName);
				cacheMap.put(userName, cacheMap.get(userName) + amount);
				cacheMap.put(Commands.prevUserName, cacheMap.get(Commands.prevUserName) + amount);
				if (cacheMap.get(userName) < 0) {
					return messageSource.getMessage(AppConstants.TOPUP_NEGATIVE_MESSAGE,
							new Object[] { amount, prevUserName, 0, Math.abs(cacheMap.get(userName)) }, Locale.ENGLISH);
				} else {
					System.out.println("test");
					return messageSource.getMessage(AppConstants.TOPUP_NEGATIVE_BALANCE_MESSAGE,
							new Object[] { Math.abs(tempValue), prevUserName, Math.abs(cacheMap.get(userName)) },
							Locale.ENGLISH);
				}

			} else {
				cacheMap.put(userName, cacheMap.get(userName) + amount);

			}
		} else {
			cacheMap.put(userName, amount);
		}

		return messageSource.getMessage(AppConstants.TOPUP_POSITIVE_MESSAGE, new Object[] { cacheMap.get(userName) },
				Locale.ENGLISH);

	}

	@ShellMethod("Transfer money Method")
	public String pay(String userName, Integer amount) {
		if (cacheMap.get(Commands.userName) >= amount) {

			if (cacheMap.get(Commands.userName) > 0 && cacheMap.get(userName) > 0) {
				cacheMap.put(userName, cacheMap.get(userName) + amount);
				cacheMap.put(Commands.userName, cacheMap.get(Commands.userName) - amount);
				return messageSource.getMessage(AppConstants.PAY_POSITIVE_MESSAGE,
						new Object[] { amount, userName, cacheMap.get(Commands.userName), }, Locale.ENGLISH);
			} else {
				cacheMap.put(userName, cacheMap.get(userName) + amount);
				// cacheMap.put(Commands.userName, cacheMap.get(Commands.userName) - amount);
				return messageSource.getMessage(AppConstants.PAY_NEGATIVE_BALANCE_MESSAGE, new Object[] {
						Math.abs(cacheMap.get(userName)),userName, cacheMap.get(Commands.userName), },
						Locale.ENGLISH);

			}
		} else {
			int transferAmount = cacheMap.get(Commands.userName);
			cacheMap.put(userName, cacheMap.get(userName) + transferAmount);
			cacheMap.put(Commands.userName, cacheMap.get(Commands.userName) - amount);
			return messageSource.getMessage(AppConstants.PAY_NEGATIVE_MESSAGE,
					new Object[] { transferAmount, userName, 0, Math.abs(cacheMap.get(Commands.userName)), userName },
					Locale.ENGLISH);
		}

	}
}
