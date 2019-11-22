package com.ego.pojo;

import java.io.Serializable;

public class AdminWithBLOBs extends Admin implements Serializable {
    private String navList;

    private String todolist;

    private static final long serialVersionUID = 1L;

    public String getNavList() {
        return navList;
    }

    public void setNavList(String navList) {
        this.navList = navList == null ? null : navList.trim();
    }

    public String getTodolist() {
        return todolist;
    }

    public void setTodolist(String todolist) {
        this.todolist = todolist == null ? null : todolist.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", navList=").append(navList);
        sb.append(", todolist=").append(todolist);
        sb.append("]");
        return sb.toString();
    }
}