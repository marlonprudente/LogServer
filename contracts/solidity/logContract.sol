pragma solidity >=0.4.22 <0.6.0;

//pragma experimental ABIEncoderV2;

contract logContract{
    
     address owner;
     
     uint256 blockContractCreationNumber;
     uint256 id = 0;
     
     mapping (uint256 => string) public  log;
     
     constructor() public { 
         owner = msg.sender; 
         blockContractCreationNumber = block.number;
     }
     
    function sendLog(string memory _logString) public {
        require (msg.sender == owner);
        log[id] = _logString;
        id++;
    }
    
    function getLogs(uint256 i) public view returns(string memory){
        return log[i];
    }
    
    function size() public view returns (uint256) {
        return id;
    }
    
    function getBlockCreationNumber() public view returns(uint256){
        return blockContractCreationNumber;
    }
}