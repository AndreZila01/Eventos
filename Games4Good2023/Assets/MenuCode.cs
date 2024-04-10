using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEditor;

public class MenuCode : MonoBehaviour
{
    public GameObject Menu;
    private GameObject painel;
	private AudioSource audioSource;
	// Start is called before the first frame update


	void Start()
	{
		audioSource = GetComponent<AudioSource>();
		double delay = 350.0; // Delay in seconds

		// Schedule the audio clip to play after the delay
		audioSource.PlayScheduled(AudioSettings.dspTime + delay);

	}

    // Update is called once per frame
    void Update()
    {
		if (Input.GetKeyDown(KeyCode.Escape) && painel!=null && painel.activeSelf)
			painel.SetActive(false);

	}

    public void StartGame()
    {
       SceneManager.LoadScene(1);
    }

    public void Quit()
    {
        UnityEngine.Application.Quit();
    }

	public void Credits(GameObject game)
	{
		painel = game;
		game.SetActive(true);
	}
}
