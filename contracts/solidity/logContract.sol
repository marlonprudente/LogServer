pragma solidity >=0.4.22 <0.6.0;

//pragma experimental ABIEncoderV2;

contract logContract{
    
     address owner;
     
     uint256 blockContractCreationNumber;
     uint256 id = 0;
     uint256 hashId = 0;
     
     mapping (uint256 => string) public  log;
     mapping (uint256 => string) public  ErroLogChain;
     mapping (uint256 => bytes32) public blockchainHash;
     bytes32 hash;
     
     constructor() public { 
         owner = msg.sender; 
         blockContractCreationNumber = block.number;
         blockchainHash[hashId] = keccak256("0");
         hashId++;
     }
     
    function sendLog(string memory _logString, bytes32 currentHash) public{
        require (msg.sender == owner);
        bytes32 blockchainCurrentHash = keccak256(abi.encodePacked(blockchainHash[id], _logString));
        if(blockchainCurrentHash != currentHash){
            ErroLogChain[id] = "Erro: este Hash e incompativel.";
            log[id] = _logString;
            id++;
        }else{
        log[id] = _logString;
        ErroLogChain[id] = "Dados armazenados com sucesso.";
        id++;
        blockchainHash[hashId] = blockchainCurrentHash;
        hashId++;
        }
    }
    function getLogs(uint256 i) public view returns(string memory){
        return log[i];
    }
    function getErroLogs(uint256 i) public view returns(string memory){
        return ErroLogChain[i];
    }
    function getLastErroLog() public view returns(string memory){
        return ErroLogChain[id - 1];
    }
    function size() public view returns (uint256) {
        return id;
    }
    function getBlockCreationNumber() public view returns(uint256){
        return blockContractCreationNumber;
    }
    function getLastHash() public view returns(bytes32){
        return blockchainHash[hashId - 1];
    }
}