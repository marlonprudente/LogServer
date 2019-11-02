pragma solidity >=0.4.22 <0.6.0;

//pragma experimental ABIEncoderV2;

contract logContract{
    
     address owner;
     
     uint256 blockContractCreationNumber;
     uint256 id = 0;
     uint256 hashId = 0;
     
     mapping (uint256 => string) public  log;
     mapping (uint256 => bytes32) public blockchainHash;
     bytes32 hash;
     
     constructor() public { 
         owner = msg.sender; 
         blockContractCreationNumber = block.number;
         blockchainHash[hashId] = keccak256("0");
         hashId++;
     }
     
    function sendLog(string memory _logString, bytes32 currentHash) public {
        require (msg.sender == owner);
        log[id] = _logString;
        id++;
        blockchainHash[id] = keccak256(abi.encodePacked(blockchainHash[id - 1], _logString));
        hashId++;
        
        if(blockchainHash[id] != currentHash){
            log[id] = "Erro ao salvar log - Hash atual incompativel.";
            id++;
        }
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
    
    function getLastHash() public view returns(bytes32){
        return blockchainHash[hashId];
    }
}