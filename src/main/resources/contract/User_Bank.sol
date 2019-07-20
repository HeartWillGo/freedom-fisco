pragma solidity ^0.4.25;

import "./Table.sol";

contract User_Bank {
    // event
    event RegisterEvent(int256 ret, string user_id, string bank_account);
    event TransferEvent(int256 ret, string from_account, string to_account, uint256 amount);
    event UpdateResult(int count);
    event RemoveResult(int count);
    constructor() public {
        // 构造函数中创建t_user_info表
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 
        // 用户信息管理表, key : user_id, value : user_value
        // |  用户信息(主键)      |     用户信息      |
        // |-------------------- |-------------------|
        // |        user_id      |    bank_account   |
        // |---------------------|-------------------|
        //
        // 创建表
        tf.createTable("t_user_bank", "user_id", "bank_account");
    }

    function openTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_user_bank");
        return table;
    }

    /*
    描述 : 用户id查询用户信息
    参数 ： 
            user_id : 用户id

    返回值：
            参数一： 成功返回0, 账户不存在返回-1
            参数二： 第一个参数为0时有效，资产金额
    */
    function select(string user_id) public constant returns(int256, string) {
        // 打开表
        Table table = openTable();
        // 查询
        Entries entries = table.select(user_id, table.newCondition());
//        if (0 == uint256(entries.size())) {
//            return (-1, user_value);
//        } else {
        Entry entry = entries.get(0);
        return (0, entry.getString("bank_account"));

    }


    //insert records
    function insert(string user_id, string bank_account) public returns(int) {
        Table table = openTable();

        Entry entry = table.newEntry();
        entry.set("user_id", user_id);
        entry.set("bank_account", bank_account);
        int ret_code = table.insert(user_id, entry);
        emit RegisterEvent(ret_code, user_id, bank_account);

        return ret_code;
    }
    //update records
    function updateBankAccount(string bankId, string bank_account,string old_bank_account) public returns(int) {
        Table table = openTable();

        Entry entry = table.newEntry();
        entry.set("user_id", bankId);
        entry.set("bank_account", bank_account);
        Condition condition = table.newCondition();
        condition.EQ("old_bank_account", old_bank_account);
        condition.EQ("user_id", bankId);

        int count = table.update(bankId, entry, condition);
        emit UpdateResult(count);

        return count;
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
