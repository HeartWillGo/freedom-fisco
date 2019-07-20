pragma solidity ^0.4.25;

import "./Table.sol";

contract BankInfo {
    // event
    event RegisterEvent(int256 ret, string bank_id, string bank_value);
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
        // |        user_id      |    user_value    |
        // |---------------------|-------------------|
        //
        // 创建表
        tf.createTable("t_bank_info", "bank_id", "bank_value");
    }

    function openTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_bank_info");
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
    function select(string bank_id) public constant returns(int256, string) {
        // 打开表
        Table table = openTable();
        // 查询
        Entries entries = table.select(bank_id, table.newCondition());
//        if (0 == uint256(entries.size())) {
//            return (-1, user_value);
//        } else {
        Entry entry = entries.get(0);
        return (0, entry.getString("bank_value"));

    }
    /*
    描述 : 用户注册
    参数 ： 
            user_id : 用户账户名
            user_value  : 资产金额
    返回值：
            0  用户注册成功
            -1 用户已存在
            -2 其他错误
    */
//    function register(string user_id, string user_value) public returns(int256){
//        int256 ret_code = 0;
//        int256 ret= 0;
//        string storage temp_asset_value= "null";
//        // 查询账号是否存在
//        (ret, temp_asset_value) = select(user_id);
//        if(ret != 0) {
//            Table table = openTable();
//
//            Entry entry = table.newEntry();
//            entry.set("user_id", user_id);
//            entry.set("user_value", user_value);
//            // 插入
//            int count = table.insert(user_id, entry);
//            if (count == 1) {
//                // 成功
//                ret_code = 0;
//            } else {
//                // 失败? 无权限或者其他错误
//                ret_code = -2;
//            }
//        } else {
//            // 账户已存在
//            ret_code = -1;
//        }
//
//        emit RegisterEvent(ret_code, user_id, user_value);
//
//        return ret_code;
//    }


    //insert records
    function insert(string bank_id, string bank_value) public returns(int) {
        Table table = openTable();

        Entry entry = table.newEntry();
        entry.set("bank_id", bank_id);
        entry.set("bank_value", bank_value);
        int ret_code = table.insert(bank_id, entry);
        emit RegisterEvent(ret_code, bank_id, bank_value);

        return ret_code;
    }
    //update records
    function updateBankAccount(string bankId, string bank_value,string old_bank_value) public returns(int) {
        Table table = openTable();

        Entry entry = table.newEntry();
        entry.set("bank_id", bankId);
        entry.set("bank_value", bank_value);
        Condition condition = table.newCondition();
        condition.EQ("old_bank_value", old_bank_value);
        condition.EQ("bank_id", bankId);

        int count = table.update(bankId, entry, condition);
        emit UpdateResult(count);

        return count;
    }
    //remove records
    function remove(string bank_id) public returns(int){
        Table table = openTable();

        Condition condition = table.newCondition();
        condition.EQ("bank_id", bank_id);

        int count = table.remove(bank_id, condition);
        emit RemoveResult(count);

        return count;
    }



}
