using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using TMPro;

public class PlayerMovement : MonoBehaviour
{
    private Rigidbody2D rb;
    public static PlayerMovement instancia;
    public bool checkpalmeira = false;
    private int tentativas = 0;
    public bool checkespada = false;
    public TextMeshProUGUI pausa;
    private bool jogoPausado = false;
	private SpriteRenderer sprite;

	private Vector3 facingRight;
	private Vector3 facingLeft;
	//public GameObject audioSource;


	void Awake()
    {
        instancia = this;
    }

    // Start is called before the first frame update
    void Start()
	{
		sprite = GetComponent<SpriteRenderer>();
		rb = GetComponent<Rigidbody2D>();
		Debug.Log("test");
    }

    // Update is called once per frame
    void Update()
	{
		float horizontal = Input.GetAxis("Horizontal");
		Flip(horizontal);

		if (Input.GetKeyDown(KeyCode.X) && checkpalmeira && tentativas > -1)
        {
            if (tentativas > 9)             
            {
                Destroy(GameObject.FindGameObjectsWithTag("Finish")[0]);
                        tentativas = -1;
            }
            else tentativas++;
        }
        else  if (Input.GetKeyDown(KeyCode.P))
            {
                if (jogoPausado)
                {

                    Time.timeScale = 1;
                    jogoPausado = false;
                }
                else
                {

                    Time.timeScale = 0;
                    jogoPausado = true;
                }
                pausa.gameObject.SetActive(jogoPausado);
            }
        
        else
        {

            Move();
            Jump();
            
        
        }
    }
    void Move()
    {
        transform.position += new Vector3(Input.GetAxis("Horizontal"), 0f, 0f) * Time.deltaTime * 5f;
    }
    void Jump()
    {
        if (Input.GetButtonDown("Jump") && transform.position.y < 1)
        {
            rb.AddForce(new Vector2(0f, 15f), ForceMode2D.Impulse);

        }
    }
	private void Flip(float horizontal)
	{
		if (horizontal > 0)
		{
			sprite.flipX = false;
		}
		else if (horizontal < 0)
		{
			sprite.flipX = true;
		}
	}
}