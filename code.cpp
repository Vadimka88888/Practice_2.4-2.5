//C++
#include <iostream>
#include <vector>

using namespace std;

void findAllPaths(const vector<vector<int>>& maze, int x, int y,
                  vector<pair<int, int>>& currentPath,
                  vector<vector<pair<int, int>>>& allPaths,
                  vector<vector<bool>>& visited) {

    int n = maze.size();
    // Добавляем текущую клетку в путь
    currentPath.push_back({x, y});

    // Базовый случай: достигли финиша
    if (x == n - 1 && y == n - 1) {
        allPaths.push_back(currentPath);
        currentPath.pop_back(); // откат
        return;
    }

    // Четыре направления: вниз, вправо, вверх, влево
    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, 1, 0, -1};

    for (int i = 0; i < 4; ++i) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 0 && nx < n && ny >= 0 && ny < n &&
            maze[nx][ny] == 0 && !visited[nx][ny]) {
            visited[nx][ny] = true;
            findAllPaths(maze, nx, ny, currentPath, allPaths, visited);
            visited[nx][ny] = false; // backtracking
        }
    }

    currentPath.pop_back(); // откат
}

int main() {
    vector<vector<int>> maze = {
        {0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0},
        {1, 0, 1, 0, 0},
        {0, 0, 0, 0, 1},
        {0, 1, 0, 0, 0}
    };

    int n = maze.size();
    vector<vector<bool>> visited(n, vector<bool>(n, false));
    visited[0][0] = true;

    vector<pair<int, int>> currentPath;
    vector<vector<pair<int, int>>> allPaths;

    findAllPaths(maze, 0, 0, currentPath, allPaths, visited);

    cout << "Найдено путей: " << allPaths.size() << "\n\n";

    for (int i = 0; i < allPaths.size(); ++i) {
        cout << "Путь " << (i + 1) << " (длина: " << allPaths[i].size() << "):\n";
        for (size_t j = 0; j < allPaths[i].size(); ++j) {
            cout << "(" << allPaths[i][j].first << "," << allPaths[i][j].second << ")";
            if (j < allPaths[i].size() - 1) cout << " -> ";
        }
        cout << "\n\n";
    }

    return 0;
}
