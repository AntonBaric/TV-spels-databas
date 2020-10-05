package com.company;

import java.sql.*;

public class Database {
    Connection conn = null;
    Database() {
        connectToDatabase();

        // updateGame(8,"Crash Bandicoot 3 Warped", 1, 6);

        // updateGenre(1, "Platformer");

        // updatePlatform(9, "Playstation 4", "2008-11-30", "Sony");

        // deleteGame(10);

        // deleteGenre(8);

        // deletePlatform(9);

        // postNewGame("Zelda: Breath of the Wild", 2, 5);

        // postNewGenre("MOBA");

        // postNewPlatform("Xbox 720","2011-11-11", "Microsoft");

        // getAllGames();

        // getAllGenres();

        // getAllPlatforms();

        getGamesAndGenre();

        // getGamesByGenre(1);

        // getGamesByPlatform(5);

    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:TVspelsBas.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateGame(int id, String title, int genre_id, int platform_id) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE games SET title = ?, genre_id = ?, platform_id = ? WHERE id = ?");
            statement.setString(1, title);
            statement.setInt(2, genre_id);
            statement.setInt(3, platform_id);
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateGenre(int id, String genre) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE genre SET genre = ? WHERE id = ?");
            statement.setString(1, genre);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updatePlatform(int id, String name, String release_date, String company) {
        try {
            PreparedStatement statement = conn.prepareStatement("Update platform SET name = ?, release_date = ?, company = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setString(2, release_date);
            statement.setString(3, company);
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteGame(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM games WHERE games.id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteGenre(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM genre WHERE genre.id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePlatform(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM platform WHERE platform.id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void postNewGame(String title, int genre_id, int platform_id) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO games (title, genre_id, platform_id) VALUES (?, ?, ?)");
            statement.setString(1, title);
            statement.setInt(2, genre_id);
            statement.setInt(3, platform_id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void postNewGenre(String genre) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO genre (genre) VALUES (?)");
            statement.setString(1, genre);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void postNewPlatform(String name, String release_date, String company) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO platform (name, release_date, company) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, release_date);
            statement.setString(3, company);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllGames() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM games");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String gamesTitle = resultSet.getString("title");

                System.out.printf("Game title: %s \n", gamesTitle);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllGenres() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM genre");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String genreGenre = resultSet.getString("genre");

                System.out.printf("Genre: %s \n", genreGenre);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllPlatforms() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM platform");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String platformName = resultSet.getString("name");
                String platformRelease_Date = resultSet.getString("release_date");
                String platformCompany = resultSet.getString("company");

                System.out.printf("Platform name: %s, release date: %s, company: %s \n", platformName, platformRelease_Date, platformCompany);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getGamesAndGenre() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM games INNER JOIN genre ON games.genre_id = genre.id WHERE genre.id");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String games = resultSet.getString("title");
                String genres = resultSet.getString("genre");

                System.out.printf("Game: %s, Platform: %s\n", games, genres);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getGamesByGenre(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM games INNER JOIN genre ON games.genre_id = genre.id WHERE genre.id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()) {
                String game = resultSet.getString("title");

                System.out.println(game);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getGamesByPlatform(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM games INNER JOIN platform ON games.platform_id = platform.id WHERE platform.id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String game = resultSet.getString("title");

                System.out.println(game);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
