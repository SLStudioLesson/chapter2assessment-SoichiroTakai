package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        searchRecipe();
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        if (fileHandler.readRecipes().isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        ArrayList<String> recipes = fileHandler.readRecipes();
        System.out.println("\n" + "Recipes:");
        System.out.println("-----------------------------------");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println(recipes.get(i));
            if (i % 2 != 0) {

                System.out.println("-----------------------------------");
            }
        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        String name = null;
        String ingredients = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter recipe name: ");
            name = br.readLine();
            System.out.print("Enter main ingredients (comma separated): ");
            ingredients = br.readLine();

        } catch (Exception e) {
        }
        fileHandler.addRecipe(name, ingredients);
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {
        System.out.println("Search Results:");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter search query (e.g., 'name=Tomato&ingredient=Garlic'): ");
            String query = br.readLine();
            ArrayList<String> keyWords=new ArrayList<>();
            
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if(keyValue.length==2){
                    keyWords.add(keyValue[1]);
                }
            }

            ArrayList<String> recipes = fileHandler.readRecipes();
            int count=0;
            for (int i=0;i<recipes.size();i=i+2) {
                    if(recipe.get(i).contains(keyWord.get(0))&&recipe.get(i+1).contains(keyWord.get(1))){
                        System.out.println(recipe);
                    }else{
                        count++;
                    }
                
            }
            
            if(count>0){
                System.out.println("No recipes found matching the criteria.");
                count=0;
            }

        } catch (Exception e) {
        }
    }

}
