//Java

import java.util.*;

public class MazeAllPaths {

    // Рекурсивная функция для поиска всех путей
    public static void findAllPaths(int[][] maze, int x, int y,
                                   List<int[]> currentPath,
                                   List<List<int[]>> allPaths,
                                   boolean[][] visited) {

        int n = maze.length;
        // Добавляем текущую позицию в путь
        currentPath.add(new int[]{x, y});

        // Базовый случай: достигли финиша (4,4)
        if (x == n - 1 && y == n - 1) {
            allPaths.add(new ArrayList<>(currentPath));
            currentPath.remove(currentPath.size() - 1); // откат
            return;
        }

        // Направления: вниз, вправо, вверх, влево
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // Проверка границ, проходимости и посещения
            if (nx >= 0 && nx < n && ny >= 0 && ny < n &&
                maze[nx][ny] == 0 && !visited[nx][ny]) {
                visited[nx][ny] = true;
                findAllPaths(maze, nx, ny, currentPath, allPaths, visited);
                visited[nx][ny] = false; // откат
            }
        }

        currentPath.remove(currentPath.size() - 1); // откат
    }

    // Основной метод
    public static void main(String[] args) {
        // 🔁 ТЕСТОВЫЙ ЛАБИРИНТ (другие значения!)
        int[][] maze = {
            {0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };

        int n = maze.length;
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true; // стартовая клетка посещена

        List<int[]> currentPath = new ArrayList<>();
        List<List<int[]>> allPaths = new ArrayList<>();

        findAllPaths(maze, 0, 0, currentPath, allPaths, visited);

        // Вывод результатов
        System.out.println("Лабиринт 5x5:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(maze[i][j] == 1 ? "# " : ". ");
            }
            System.out.println();
        }

        System.out.println("\nПоиск всех путей...\n");
        System.out.println("Найдено путей: " + allPaths.size() + "\n");

        for (int i = 0; i < allPaths.size(); i++) {
            List<int[]> path = allPaths.get(i);
            System.out.println("Путь " + (i + 1) + " (длина: " + path.size() + "):");
            for (int j = 0; j < path.size(); j++) {
                int[] pos = path.get(j);
                System.out.print("(" + pos[0] + "," + pos[1] + ")");
                if (j < path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println("\n");
        }
    }
}
