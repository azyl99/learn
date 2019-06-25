package solved;

import utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/find-duplicate-file-in-system/
 *
 * 给定一个目录信息列表，包括目录路径，以及该目录中的所有包含内容的文件，您需要找到文件系统中的所有重复文件组的路径。一组重复的文件至少包括二个具有完全相同内容的文件。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-duplicate-file-in-system
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author guya on 2019/6/25
 */
class Medium_easy_重复文件 {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            parse(map, path);
        }
        List<List<String>> result = new ArrayList<>();
        for (String content : map.keySet()) {
            List<String> list = map.get(content);
            if (list.size() >= 2) {
                result.add(list);
            }
        }

        return result;
    }

    private void parse(Map<String, List<String>> map, String path) {
        String[] arr = path.split(" ");
        String dir = arr[0];
        for (int i = 1; i < arr.length; i++) {
//            String[] temp = arr[i].split("\\(|\\)");
//            String fileName = temp[0];
//            String content = temp[1];
            String temp = arr[i];
            int index = temp.indexOf('(');
            // 避免耗时较大的 regex split 操作
            String fileName = temp.substring(0, index);
            // 可以把括号一起看成内容
            String content = temp.substring(index);
            addToMap(map, dir + "/" + fileName, content);
        }
    }

    private void addToMap(Map<String,List<String>> map, String path, String content) {
        map.putIfAbsent(content, new ArrayList<>());
        map.get(content).add(path);
    }


    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
//        String[] paths = {"root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"};
        List<List<String>> lists = new Medium_easy_重复文件().findDuplicate(paths);
        System.out.println(JsonUtils.toJson(lists));
    }
}
