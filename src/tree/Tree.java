package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据实际项目需要生成部门树形结构
 */
public class Tree {

    static class DeptTree {
        private String title;
        private Integer key;
        private Integer parent;
        private List<DeptTree> children;

        public List<DeptTree> getChildren() {
            return children;
        }

        public void setChildren(List<DeptTree> children) {
            this.children = children;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getParent() {
            return parent;
        }

        public void setParent(Integer parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "{" +
                    "title:'" + title + '\'' +
                    ",\n key:" + key +
                    ", '\n'parent:" + parent +
                    ", '\n'children:" + children +
                    '}';
        }
    }

    static class QyDepartment {
        private String name;
        private Integer parentid;
        private Integer departmentid;

        public QyDepartment(String name, Integer parentid, Integer departmentid){
            this.name = name;
            this.parentid = parentid;
            this.departmentid = departmentid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getParentid() {
            return parentid;
        }

        public void setParentid(Integer parentid) {
            this.parentid = parentid;
        }

        public Integer getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(Integer departmentid) {
            this.departmentid = departmentid;
        }
    }

    public static void main(String[] args) {
        List<QyDepartment> list = new ArrayList<>();
        QyDepartment one = new QyDepartment("root", 0, 1);
        list.add(one);
        QyDepartment two1 = new QyDepartment("tow1", 1, 2);
        list.add(two1);
        QyDepartment two2 = new QyDepartment("two2", 1, 3);
        list.add(two2);
        QyDepartment three11 = new QyDepartment("three11", 2, 4);
        list.add(three11);
        QyDepartment three12 = new QyDepartment("three12", 2, 5);
        list.add(three12);
        QyDepartment three21 = new QyDepartment("three21", 3, 6);
        list.add(three21);
        DeptTree deptTree = buildTree(list);
        System.out.println(deptTree.toString());
        /**
         * {
         *     title: 'root',
         *     key: 1,
         *     parent: 0,
         *     children: [
         *         {
         *             title: 'tow1',
         *             key: 2,
         *             parent: 1,
         *             children: [
         *                 {
         *                     title: 'three11',
         *                     key: 4,
         *                     parent: 2,
         *                     children: [
         *
         *                     ]
         *                 },
         *                 {
         *                     title: 'three12',
         *                     key: 5,
         *                     parent: 2,
         *                     children: [
         *
         *                     ]
         *                 }
         *             ]
         *         },
         *         {
         *             title: 'two2',
         *             key: 3,
         *             parent: 1,
         *             children: [
         *                 {
         *                     title: 'three21',
         *                     key: 6,
         *                     parent: 3,
         *                     children: [
         *
         *                     ]
         *                 }
         *             ]
         *         }
         *     ]
         * }
         */
    }

    private static DeptTree buildTree(List<QyDepartment> list) {
        if (list != null) {
            // 先形成一个map
            Map<Integer, DeptTree> map = new HashMap<>();
            for (QyDepartment dept : list) {
                DeptTree deptTree = new DeptTree();
                deptTree.setChildren(new ArrayList<>());
                deptTree.setKey(dept.getDepartmentid());
                deptTree.setTitle(dept.getName());
                deptTree.setParent(dept.getParentid());
                map.put(dept.getDepartmentid(), deptTree);
            }
            // 根据map生成树
            for (Integer i : map.keySet()) {
                DeptTree child = map.get(i);
                if (child.getKey() > 1) { // 非根
                    DeptTree parent = map.get(child.getParent());
                    parent.getChildren().add(child);
                }
            }
            return map.get(1);
        } else {
            return null;
        }
    }

}
