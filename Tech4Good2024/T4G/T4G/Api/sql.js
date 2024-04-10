const sql = require("mssql/msnodesqlv8");

// const config = {
//   server: '(127.0.1.1)\MSSQLLocalDB',
//   database: 'CASA',
//   driver: 'msnodesqlv8',
//   options: {
//     trustedConnection: true // Use Windows Authentication
//   }
// };
const config = {
  server: '(LocalDb)\\MSSQLLocalDB',
  database: 'CASA',
  options: {
    trustedConnection: true
  }
};

async function executeQuery() {
  try {

    const result = await sql.query`SELECT * FROM tblRoles`;
    console.log('Dados:', result.recordset);
    console.log('');

  } catch (err) {
    console.error('Query error:', err);
  }
}


async function test(query){
  let s = "";
  await db.query(query, (err, result) =>{
      if(err)
          throw err;
      s = JSON.stringify(result);
  });
  while (s=="")
   await new Promise(resolve => setTimeout(resolve, 100));
  
  return s;
}
async function Select(query){
  
  let s = "";
  await db.query(query, (err, result) =>{
      if(err)
          throw err;
      s = JSON.stringify(result);
  });
  while (s=="")
   await new Promise(resolve => setTimeout(resolve, 100));
  
  return s;
}

async function PutValues(query){
  let s = "";
  await db.query(query, (err, result) =>{
      if(err)
          throw err;
      s = result.affectedRows == 1 ? s= true: s= false; 
  });
  while (s=="")
   await new Promise(resolve => setTimeout(resolve, 100));
  
  return s;
}

async function SelectCompare(query){
  let s = "";
  await db.query(query, (err, result) =>{
      if(err)
          throw err;
      s = result.length==1? s = result[0].idLogin : s = -1;
  });
  while (s=="")
   await new Promise(resolve => setTimeout(resolve, 100));
  
  return s;
}

async function test(query) {
  try {
    let s = "";
    const result = await sql.query(query);
    s = JSON.stringify(result.recordset);
    return s;
  } catch (error) {
    throw error;
  }
}

async function Select(query) {
  try {
    let s = "";
    const result = await sql.query(query);
    s = JSON.stringify(result.recordset);
    return s;
  } catch (error) {
    throw error;
  }
}

async function PutValues(query) {
  try {
    let s = "";
    const result = await sql.query(query);
    s = result.rowsAffected[0] == 1 ? true : false;
    return s;
  } catch (error) {
    throw error;
  }
}

async function SelectCompare(query) {
  try {
    let s = "";
    const result = await sql.query(query);
    s = result.recordset.length == 1 ? result.recordset[0].idLogin : -1;
    return s;
  } catch (error) {
    throw error;
  }
}

async function connection() {

  await sql.connect(config);
}

module.exports = { executeQuery, connection, Select, SelectCompare, PutValues };