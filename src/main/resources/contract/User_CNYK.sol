pragma solidity ^0.4.25;

import "./Table.sol";

contract User_CNYK {
    // event
    event RegisterEvent(int256 ret, string user_id, string user_value);
    event TransferEvent(int256 ret, string from_account, string to_account, uint256 amount);
    event UpdateResult(int count);
    event RemoveResult(int count);
    constructor() public {
        // 构造函数中创建t_user_info表
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 
        // 用户信息管理表, key : user_id, value : cnyk_account
        // |  用户信息(主键)      |     账户信息      |
        // |-------------------- |-------------------|
        // |        user_id      |    cnyk_account   |
        // |---------------------|-------------------|
        //
        // 创建表
        tf.createTable("t_user_cnyk", "user_id", "cnyk_account");
    }

    function openTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_user_cnyk");
        return table;
    }


    //insert records
    function insert(string user_id, string cnyk_account) public returns(int) {
        Table table = openTable();

        Entry entry = table.newEntry();
        entry.set("user_id", user_id);
        entry.set("cnyk_account", cnyk_account);

        int ret_code = table.insert(user_id, entry);
        emit RegisterEvent(ret_code, user_id, cnyk_account);

        return ret_code;
    }

    //remove records
    function remove(string user_id) public returns(int){
        Table table = openTable();

        Condition condition = table.newCondition();
        condition.EQ("user_id", user_id);

        int count = table.remove(user_id, condition);
        emit RemoveResult(count);

        return count;
    }


}
