import torch
from transformers import AutoTokenizer, GPT2LMHeadModel

if torch.cuda.is_available():
    torch_device = 'cuda'
else: 
    torch_device = "cpu"
    
tokenizer = AutoTokenizer.from_pretrained("gpt2")
model = GPT2LMHeadModel.from_pretrained("gpt2", pad_token_id=tokenizer.eos_token_id).to(torch_device)

torch.manual_seed(0)

model_inputs = tokenizer('I enjoy walking with my cute dog, but I\'m not sure if I\'ll ever be able to walk with him again.', return_tensors='pt').to(torch_device)

output = model.generate(
    **model_inputs,
    max_length=200,
    num_beams=5,
    num_return_sequences=1,
    no_repeat_ngram_size=2,
    temperature=0.7,
    top_k=50,
    top_p=0.95
)

print("\n" + 100 * '-')
print(tokenizer.decode(output[0], skip_special_tokens=True))
print(100 * '-')
