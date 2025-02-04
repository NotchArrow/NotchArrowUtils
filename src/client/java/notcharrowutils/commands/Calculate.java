package notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;

import java.util.Stack;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Calculate {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("calculate")
				.then(argument("expression", StringArgumentType.greedyString())
						.executes(Calculate::execute));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		String expression = StringArgumentType.getString(context, "expression");
		try {
			client.player.sendMessage(TextFormat.styledText("Result: " + MathEvaluator.evaluate(expression)));
			return 1;
		} catch (Exception e) {
			client.player.sendMessage(TextFormat.styledText("Invalid equation: " + e.getMessage()));
			return 0;
		}
	}
}

class MathEvaluator {
	// Eventually rewrite to be more readable and add constants

	public static double evaluate(String expr) {
		expr = expr.replaceAll(" ", ""); // Remove spaces
		Stack<Double> numbers = new Stack<>();
		Stack<Character> operators = new Stack<>();

		for (int i = 0; i < expr.length(); i++) {
			char c = expr.charAt(i);

			if (Character.isDigit(c) || c == '.') { // Parse number
				int j = i;
				while (j < expr.length() && (Character.isDigit(expr.charAt(j)) || expr.charAt(j) == '.')) j++;
				numbers.push(Double.parseDouble(expr.substring(i, j)));
				i = j - 1;
			} else if (c == '(') {
				operators.push(c);
			} else if (c == ')') {
				while (operators.peek() != '(') numbers.push(apply(operators.pop(), numbers.pop(), numbers.pop()));
				operators.pop(); // Remove '('
			} else if ("+-*/".indexOf(c) >= 0) { // Handle operators
				while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c))
					numbers.push(apply(operators.pop(), numbers.pop(), numbers.pop()));
				operators.push(c);
			} else {
				throw new IllegalArgumentException("Invalid character: " + c);
			}
		}

		while (!operators.isEmpty()) numbers.push(apply(operators.pop(), numbers.pop(), numbers.pop()));
		return numbers.pop();
	}

	private static int precedence(char op) {
		return (op == '+' || op == '-') ? 1 : 2;
	}

	private static double apply(char op, double b, double a) {
		return switch (op) {
			case '+' -> a + b;
			case '-' -> a - b;
			case '*' -> a * b;
			case '/' -> {
				if (b == 0) throw new IllegalArgumentException("Division by zero");
				yield a / b;
			}
			default -> throw new IllegalArgumentException("Invalid operator: " + op);
		};
	}
}
