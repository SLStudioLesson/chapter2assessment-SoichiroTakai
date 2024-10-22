package data;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br> 
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String> recipes = new ArrayList<>();
            ArrayList<String> temp = new ArrayList<>();
            int a ;
            while((line = reader.readLine()) != null){
                a = line.indexOf(',');
                recipes.add("Recipe Name: "+line.substring(0, a));
                recipes.add("Main Ingredients: "+line.substring(a+1));
            }
            return recipes;
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        return null;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     // 
    public void addRecipe(String recipeName, String ingredients) {
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath,true))) {
            fileWriter.write(recipeName+",");
            fileWriter.write(ingredients);
            fileWriter.newLine();
            System.out.println("Recipe added successfully.");

        } catch (IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
    }
}
