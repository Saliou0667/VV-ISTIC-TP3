package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if (str == null) return false; // Une chaîne nulle n'est pas équilibrée

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            // Si c est un ouvrant, on empile
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                // Si c est un fermant, on vérifie si la pile n'est pas vide et correspond
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (!matches(top, c)) return false;
            } else {
                // Caractère autre, on l'ignore (si la consigne l'autorise)
                // Sinon, on pourrait retourner false
            }
        }
        return stack.isEmpty();
    }

    // Méthode  pour vérifier la correspondance
    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

}
